package com.mogujie.tt;

public class Security {

    private static Security m_pInstance;

    static {
        System.loadLibrary("security");
    }

    public static Security getInstance() {
        synchronized (Security.class) {
            if (m_pInstance == null) {
                m_pInstance = new Security();
            }

            return m_pInstance;
        }
    }

    public native byte[] DecryptMsg(String strMsg);

    public native byte[] EncryptMsg(String strMsg);

    public native byte[] EncryptPass(String strPass);
}
