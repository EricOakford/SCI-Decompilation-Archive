;;; Sierra Script 1.0 - (do not remove this comment)
(script# 961)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)
(use System)

(public
	proc961_0 0
)

(procedure (proc961_0 thePROPERTY_ACCESS_IN_NON_METHOD thePROPERTY_ACCESS_IN_NON_METHOD_2 thePROPERTY_ACCESS_IN_NON_METHOD_3 thePROPERTY_ACCESS_IN_NON_METHOD_4)
	(= PROPERTY-ACCESS-IN-NON-METHOD
		thePROPERTY_ACCESS_IN_NON_METHOD
	)
	(= PROPERTY-ACCESS-IN-NON-METHOD
		thePROPERTY_ACCESS_IN_NON_METHOD_2
	)
	(= PROPERTY-ACCESS-IN-NON-METHOD
		thePROPERTY_ACCESS_IN_NON_METHOD_3
	)
	(= PROPERTY-ACCESS-IN-NON-METHOD
		thePROPERTY_ACCESS_IN_NON_METHOD_4
	)
	(super init: &rest)
)

(instance SkeletonHead of View
	(properties
		fixPriority 1
		view 14170
		loop 1
	)
	
	(method (init theX theY thePriority theCel)
		(= x theX)
		(= y theY)
		(= priority thePriority)
		(= cel theCel)
		(super init: &rest)
	)
)

(instance SkeletonButton of ShiversProp
	(properties
		fixPriority 1
		view 14170
		loop 2
	)
	
	(method (init theX theY thePriority)
		(= x theX)
		(= y theY)
		(= priority thePriority)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom setScript: sPushButton)
	)
)

(class SkeletonDialPuzzle of ShiversProp
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
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
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 1
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view 14170
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		cycleSpeed 18
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		curColor 0
		whichDial 0
	)
	
	(method (init theX theY thePriority theWhichDial)
		(= x theX)
		(= y theY)
		(= priority thePriority)
		(= whichDial theWhichDial)
		(= curColor [gCurColor whichDial])
		(SkeletonHead init: theX theY (+ thePriority 1) curColor)
		(SkeletonHead hide:)
		(SkeletonButton init: theX theY (+ thePriority 5))
		(cond 
			((== whichDial 0) (self view: 11330))
			((== whichDial 1) (self view: 14170))
			((== whichDial 5) (self view: 23650))
			((== whichDial 3) (self view: 21400))
			((== whichDial 4) (self view: 20190))
			((== whichDial 2) (self view: 24171))
		)
		(SkeletonHead view: (self view?))
		(SkeletonButton view: (self view?) setLoop: 2 1)
		(= cel (self lastCel:))
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom setScript: sTurnDial)
	)
	
	(method (isSolved &tmp temp0)
		(= temp0 0)
		(while (< temp0 4)
			(if (!= [gCurColor temp0] [global334 temp0]) (return 0))
			(++ temp0)
		)
		(return 1)
	)
)

(instance sTurnDial of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (SkeletonDialPuzzle cel?)
					(SkeletonHead hide:)
					(SkeletonDialPuzzle cel: 0)
				)
				(= cycles 1)
			)
			(1
				(sounds stop: 15002)
				(sounds play: 15002 0 127 0)
				(SkeletonDialPuzzle setCycle: End)
				(= cycles 1)
			)
			(2
				(SkeletonHead cel: (mod (+ (SkeletonHead cel?) 1) 4))
				(= seconds 3)
			)
			(3
				(SkeletonHead show:)
				(= ticks 60)
			)
			(4
				(SkeletonHead hide:)
				(= [gCurColor (SkeletonDialPuzzle whichDial?)]
					(SkeletonHead cel?)
				)
				(if
					(!=
						(SkeletonHead cel?)
						[global334 (SkeletonDialPuzzle whichDial?)]
					)
					(proc951_4 78)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPushButton of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sounds stop: 15029)
				(= cycles 1)
			)
			(1
				(sounds play: 15029 0 127 0)
				(SkeletonHead show:)
				(= ticks 60)
			)
			(2
				(theGame handsOn:)
				(SkeletonHead hide:)
				(self dispose:)
			)
		)
	)
)
