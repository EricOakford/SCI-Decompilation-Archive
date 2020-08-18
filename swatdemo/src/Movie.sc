;;; Sierra Script 1.0 - (do not remove this comment)
(script# MOVIE)
(include game.sh)
(use Main)
(use Print)
(use System)


(class Movie of Object
	(properties
		number 1
		x 0
		y 0
		startFrame 0
		curFrame -1
		endFrame 0
		modeless 0
		caller 0
		inited 0
		error 0
	)
	
	(method (init theName)
		(= inited TRUE)
		(if modeless
			(theDoits add: self)
		)
		(if (and argc theName)
			(= name theName)
		)
		(self open:)
	)
	
	(method (doit &tmp status)
		(if (= status (self getStatus:))
			(= curFrame (ShowMovie number AVI_GET_POSITION))
			(if (and modeless (== status 5))
				(self close:)
			)
		)
	)
	
	(method (setPalette)
		(= error (ShowMovie number AVI_SETPALETTE))
		(self displayError:)
	)
	
	(method (open theName)
		(if (and argc theName)
			(= name theName)
		)
		(if (not inited)
			(self init:)
		)
		(= error (ShowMovie number AVI_OPEN name))
		(self displayError:)
		(= endFrame (ShowMovie number AVI_GET_LENGTH))
	)
	
	(method (posn where &tmp theX theY)
		(= x [where 0])
		(= y [where 1])
		(= theX (= theY 0))
		(if (> argc 2)
			(= theX [where 2])
			(if (> argc 3) (= theY [where 3]))
		)
		(= error (ShowMovie number AVI_PUT x y theX theY))
		(self displayError:)
	)
	
	(method (play theName)
		(if (and argc theName)
			(= name theName)
		)
		(if (not inited)
			(self open:)
		)
		(= error
			(ShowMovie number AVI_PLAY startFrame endFrame 2 modeless)
		)
		(self displayError:)
	)
	
	(method (close)
		(if inited
			(ShowMovie number AVI_CLOSE)
			(if modeless
				(theDoits delete: self)
			)
			(= inited FALSE)
			(if caller
				(if (not cuees)
					(= cuees (Set new:))
				)
				(cuees add: ((Cue new:) cuee: caller yourself:))
				(= caller 0)
			)
		)
	)
	
	(method (stop)
		(self pause:)
	)
	
	(method (pause value)
		(if (and argc (not value))
			(= error (ShowMovie number AVI_RESUME))
		else
			(= error (ShowMovie number AVI_PAUSE))
		)
		(self displayError:)
	)
	
	(method (resume)
		(self pause: FALSE)
	)
	
	(method (cueUp param1)
		(= error (ShowMovie number AVI_CUE param1))
		(self displayError:)
	)
	
	(method (setModeless value)
		(= modeless (if (not argc) else value))
	)
	
	(method (seek param1)
		(ShowMovie number AVI_SEEK param1)
	)
	
	(method (displayError)
		(if (and error debugging)
			(Printf {Movie Error: %d} error)
		)
	)
	
	(method (getStatus)
		(ShowMovie number AVI_GET_STATUS)
	)
)
