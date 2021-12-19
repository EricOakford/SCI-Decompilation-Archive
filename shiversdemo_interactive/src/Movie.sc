;;; Sierra Script 1.0 - (do not remove this comment)
(script# 962)
(include game.sh)
(use Procs)
(use String)
(use System)


(class Movie of Object
	(properties
		number 0
		x 0
		y 0
		client 0
		caller 0
		opened 0
		paused 0
		type $0001
	)
	
	(method (dispose)
		(= y (= x 0))
		(if client
			(client movie: 0)
		)
		(if caller
			(caller cue:)
			(= caller 0)
		)
	)
	
	(method (play movieNum whoCares theX theY &tmp str)
		(if (and (> argc 1) whoCares)
			(= caller whoCares)
		)
		(if (and (> argc 2) theX)
			(self posn: theX theY)
		)
		(if (not opened)
			(self open: movieNum)
		)
		(= str
			(String format:
				(switch type
					(0 {%d.mov})
					(1 {%d.avi})
				) number
			)
		)
		(ShowMovie 1 AVI_PLAY (str data?))
		(str dispose:)
		(self close: dispose:)
	)
	
	(method (open theNumber theX theY &tmp str)
		(= number theNumber)
		(= str
			(String
				format:
				(switch type
					(0 {%d.mov})
					(1 {%d.avi})
				) number
			)
		)
		(ShowMovie 1 AVI_OPEN (str data?))
		(if (and (> argc 1) theX) (self posn: theX theY))
		(if (proc951_5 38)
			(ShowMovie 1 AVI_PUT_DOUBLED x y)
		else
			(ShowMovie 1 AVI_PUT x y)
		)
		(= opened TRUE)
		(str dispose:)
	)
	
	(method (close &tmp str)
		(= str
			(String
				format:
				(switch type
					(0 {%d.mov})
					(1 {%d.avi})
				) number
			)
		)
		(ShowMovie 1 AVI_CLOSE (str data?))
		(= opened 0)
		(str dispose:)
	)
	
	(method (posn theX theY)
		(= x theX)
		(= y theY)
	)
	
	(method (setPal movieNum &tmp str)
		(= str (String format: {%d.pal} movieNum))
		(ShowMovie 1 AVI_SETPALETTE (str data?))
		(str dispose:)
	)
	
	(method (putDouble theX theY)
		(if (and (> argc 1) theX) (= x theX) (= y theY))
		(ShowMovie 1 AVI_PUT_DOUBLED x y)
	)
	
	(method (put theX theY)
		(if (and (> argc 1) theX) (= x theX) (= y theY))
		(ShowMovie 1 AVI_PUT x y)
	)
	
	(method (pause)
		(if (not paused)
			(ShowMovie 1 AVI_PAUSE)
			(= paused TRUE)
		)
	)
	
	(method (setWaitEvent param1 param2)
		(if (and (> argc 0) param1)
			(ShowMovie 1 AVI_WAIT_EVENT
				param1
				(if (and (> argc 1) param2) param2 else 0)
			)
		else
			(ShowMovie 1 AVI_WAIT_EVENT)
		)
	)
	
	(method (resume)
		(if paused
			(ShowMovie 1 AVI_RESUME)
			(= paused FALSE)
		)
	)
)

(class VmdMovie of Movie
	(properties
		type $0002
		size 0
	)
	
	(method (play theX theY)
		(if argc (PlayVMD 2 theX theY) else (PlayVMD 2))
	)
	
	(method (open theNumber theSize whoCares &tmp str)
		(= number theNumber)
		(if (and (> argc 0) theSize)
			(= size theSize)
			(if (and (> argc 1) whoCares) (= caller whoCares))
		)
		(= str (String format: {%d.vmd} number))
		(PlayVMD VMD_OPEN (str data?) size)
		(str dispose:)
		(= opened 1)
	)
	
	(method (close)
		(PlayVMD VMD_CLOSE)
		(= opened FALSE)
	)
	
	(method (posn)
		(super posn: &rest)
		(PlayVMD VMD_PUT x y)
	)
	
	(method (setPal)
		(PlayVMD VMD_SETPALETTE)
	)
	
	(method (putDouble)
	)
	
	(method (put theX theY param3 param4)
		(if (not number) (return))
		(if (and theX theY (> argc 0))
			(self posn: theX theY)
			(if (and (> argc 1) param3)
				(PlayVMD VMD_PUT x y
					param3
					(if (and (> argc 2) param4) param4 else 0)
				)
			)
		)
	)
	
	(method (setWaitEvent param1 param2)
		(if (and (> argc 0) param1)
			(PlayVMD VMD_WAIT_EVENT
				param1
				(if (and (> argc 1) param2) param2 else 0)
			)
		else
			(PlayVMD VMD_WAIT_EVENT)
		)
	)
	
	(method (black param1 param2 param3 param4)
		(PlayVMD VMD_BLACK param1 param2 param3 param4)
	)
	
	(method (getStatus)
		(PlayVMD VMD_GET_STATUS)
	)
	
	(method (stop theNumber &tmp str)
		(if (and (> argc 0) theNumber) (= number theNumber))
		(= str (String format: {%d.vmd} number))
		(PlayVMD VMD_STOP (str data?))
		(str dispose:)
	)
	
	(method (showCursor)
		(PlayVMD VMD_SHOW_CURSOR 1)
	)
)
