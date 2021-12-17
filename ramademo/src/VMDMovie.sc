;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1112)
(include game.sh)
(use Main)
(use String)
(use System)


(class VMDMovie of Object
	(properties
		scratch 0
		movieName 0
		options 0
		endPic 0
		x 30
		y 39
		cacheSize 0
		restrictPallete TRUE
		colorLow 0
		colorHigh 191
		waitEvents 3
		top 39
		left 30
		bottom 39
		right 30
		showCursor TRUE
	)
	
	(method (play param1 param2 param3 &tmp temp0 temp1)
		(= temp1
			((= temp0 (String format: {%s} movieName)) asInteger:)
		)
		(temp0 dispose:)
		(if (and temp1 (not (ResCheck 151 temp1)))
			(MonoOut {Unable to find %d.vmd} temp1)
			(return)
		)
		(if (not (PlayVMD VMD_GET_STATUS))
			(self open:)
		)
		(PlayVMD 23 colorLow colorHigh)
		(PlayVMD VMD_BLACK top left bottom right)
		(PlayVMD VMD_SHOW_CURSOR showCursor)
		(switch argc
			(0
				(PlayVMD VMD_PUT x y options)
			)
			(1
				(PlayVMD VMD_PUT x y options param1)
			)
			(3
				(PlayVMD VMD_PUT x y options param1 param2 param3)
			)
			(else
				(PlayVMD VMD_PUT x y options)
			)
		)
		(PlayVMD VMD_WAIT_EVENT waitEvents)
		(self doVerb: 3)
		(if endPic
			(curRoom drawPic: endPic)
		)
		(PlayVMD VMD_CLOSE)
	)
	
	(method (open)
		(PlayVMD VMD_OPEN movieName cacheSize)
	)
	
	(method (doVerb)
	)
)
