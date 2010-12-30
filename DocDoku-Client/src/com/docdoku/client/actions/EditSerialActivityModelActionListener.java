/*
 * DocDoku, Professional Open Source
 * Copyright 2006, 2007, 2008, 2009, 2010 DocDoku SARL
 *
 * This file is part of DocDoku.
 *
 * DocDoku is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DocDoku is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DocDoku.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.docdoku.client.actions;

import com.docdoku.client.ui.workflow.EditSerialActivityModelDialog;
import com.docdoku.core.entities.SerialActivityModel;
import com.docdoku.core.entities.WorkflowModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.docdoku.client.ui.workflow.EditableSerialActivityModelCanvas;
import com.docdoku.client.ui.workflow.WorkflowModelFrame;

import javax.swing.*;

public class EditSerialActivityModelActionListener implements ActionListener {


    public void actionPerformed(ActionEvent pAE) {
        final EditableSerialActivityModelCanvas canvas = (EditableSerialActivityModelCanvas) pAE.getSource();
        final WorkflowModelFrame owner = (WorkflowModelFrame) SwingUtilities.getAncestorOfClass(WorkflowModelFrame.class, canvas);
        //clone the object in case the user cancels the action
        final SerialActivityModel clonedActivityModel = canvas.getSerialActivityModel().clone();
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent pAE) {
                WorkflowModel model = owner.getWorkflowModel();
                int step = canvas.getSerialActivityModel().getStep();
                model.setActivityModel(step,clonedActivityModel);
                canvas.setSerialActivityModel(clonedActivityModel);
                canvas.refresh();
            }
        };
        new EditSerialActivityModelDialog(owner, clonedActivityModel, new CreateTaskActionListener(), new EditTaskActionListener(), action);
    }

}