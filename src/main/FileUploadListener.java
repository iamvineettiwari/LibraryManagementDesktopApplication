/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import it.sauronsoftware.ftp4j.FTPDataTransferListener;
/**
 *
 * @author Manas
 */
public class FileUploadListener implements FTPDataTransferListener {

	public void started() {
		// Transfer started
	}

	public void transferred(int length) {
		// Yet other length bytes has been transferred since the last time this
		// method was called
	}

	public void completed() {
            home.photoUploaded = true;
	}

	public void aborted() {
		// Transfer aborted
	}

	public void failed() {
		// Transfer failed
	}

}