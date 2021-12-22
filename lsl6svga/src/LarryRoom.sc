;;; Sierra Script 1.0 - (do not remove this comment)
(script# 84)
(include sci.sh)
(use Main)
(use fileScr)
(use Game)
(use System)

(public
	LarryRoom 0
)

(class LarryRoom of Room
	(properties
		scratch 0
		script 0
		number 0
		modNum -1
		noun 0
		case 0
		timer 0
		keep 0
		initialized 0
		picture -1
		plane 0
		style $0000
		exitStyle -1
		horizon 80
		controls 0
		north 0
		east 0
		south 0
		west 0
		curPic 0
		purge 500
		picAngle 0
		vanishingX 160
		vanishingY 0
		obstacles 0
		inset 0
		autoLoad 1
		showControls 0
		noControls 0
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
				(= temp0
					(self edgeToRoom: ((user alterEgo?) edgeHit?))
				)
				(self newRoom: temp0)
			)
		)
	)
	
	(method (dispose)
		(if global205 (proc79_7))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(cond 
				((super doVerb: theVerb) (return 1))
				(
					(and
						(not (OneOf theVerb 4 1 2 5 6))
						(Message 0 modNum noun 15 0 1)
					)
					(messager say: noun 15 0)
					(return 1)
				)
				((Message 0 modNum noun 0 0 1) (messager say: noun 0 0) (return 1))
				(else (return 0))
			)
		)
	)
	
	(method (newRoom)
		(theGame handsOff:)
		(super newRoom: &rest)
	)
)
