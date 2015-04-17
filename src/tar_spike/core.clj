(ns tar-spike.core
  (:import [org.apache.commons.compress.archivers.tar
            TarArchiveOutputStream
            TarArchiveInputStream
            TarArchiveEntry]
           [java.io
            File
            FileInputStream
            FileOutputStream]
           [java.util
            Date]))

;; spike: write tar file, read tar file back

(comment

  (let [file (File. "testarchive.tar")]
    (with-open [fos (FileOutputStream. file)
                tos (TarArchiveOutputStream. fos)]

      (let [e (doto (TarArchiveEntry. (File. (File. "/") "foo.txt"))
                (.setSize 5))]
        (doto tos
          (.putArchiveEntry e)
          (.write (.getBytes "hello"))
          (.closeArchiveEntry)))

      (let [tomorrow (Date. (long (+ (System/currentTimeMillis) (* 1000 60 60 24))))
            e (doto (TarArchiveEntry. (File. (File. "/folder/") "bar.txt"))
                (.setSize 6)
                (.setModTime tomorrow))]
        (doto tos
          (.putArchiveEntry e)
          (.write (.getBytes "world!"))
          (.closeArchiveEntry)))))

  )
