;;; Sierra Script 1.0 - (do not remove this comment)
(script# DISPOSE)
(include game.sh)
(use LoadMany)
(use System)

(public
	disposeCode 0
)

(instance disposeCode of Code
	
	(method (doit)
		(LoadMany FALSE
			1880 1881 1882 1883 1884 1885 1886 1887 1888
			1889 1890 1891 1892 1893 1894 1895 1896 1897
			1899 1900 1901 1902 1903 1904 1905 1906 1907
			32 35 31 36 37 DIALOG INSET FILE CONV
			20 23 24 22 SLIDEICON PAVOID PCHASE SCALER 27
			DPATH 21 30 919 16 DOOR MOVEFWD FORCOUNT 942
			REVERSE RANDCYC PFOLLOW WRITEFTR
		)
		(DisposeScript DISPOSE)
	)
)
