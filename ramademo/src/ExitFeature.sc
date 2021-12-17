;;; Sierra Script 1.0 - (do not remove this comment)
(script# 60)
(include sci.sh)
(use Main)
(use Feature)
(use Actor)

(public
	ExitFeature 0
)

(class ExitFeature of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 50
		nsTop 42
		nsRight 582
		nsBottom 282
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		nextRoom 0
		exitCursor 0
		verb 9
		exitVMD 0
		detailLevel 1
	)
	
	(method (init theExitVMD theExitCursor param3 param4)
		(if argc (= exitVMD theExitVMD))
		(cond 
			((> argc 1)
				(= exitCursor theExitCursor)
				(if (== argc 3)
					(if exitCursor (exitCursor dispose:))
					((= exitCursor (View new:))
						view: theExitCursor
						loop: param3
						cel: param4
					)
				)
			)
			((not exitCursor) (= exitCursor defaultExitView))
		)
		(= plane gTopPlane)
		(self setHotspot: 9)
		(super init: &rest)
	)
	
	(method (dispose)
		(exitCursor dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(event localize: plane)
		(if
			(and
				(self onMe: event)
				(not (event type?))
				(not (theCursor isInvCursor:))
			)
			(theCursor setDirCursor: exitCursor verb: verb)
			(event claimed: 1)
			(return)
		else
			(event globalize:)
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(9
				(if
				(and exitVMD (<= (theGame _detailLevel?) detailLevel))
					(exitVMD play:)
				)
				(curRoom newRoom: nextRoom)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (enable)
		(self setHotspot: 9)
	)
	
	(method (disable)
		(self deleteHotspot:)
	)
)

(instance defaultExitView of View
	(properties
		view 600
		cel 6
	)
)
