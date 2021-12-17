;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11200)
(include sci.sh)
(use Main)
(use TPRoom)
(use CueMe)
(use ExitFeature)
(use Polygon)
(use Motion)
(use Actor)

(public
	roSewingBasket 0
)

(local
	local0
	local1
	local2
)
(instance oNotInchie of Polygon
	(properties)
	
	(method (init)
		(super
			init:
				436
				243
				412
				237
				375
				232
				338
				226
				311
				220
				292
				214
				264
				204
				271
				116
				206
				122
				150
				305
				461
				306
		)
	)
)

(instance poInchworm of Prop
	(properties
		noun 1
		x 325
		y 279
		view 11200
	)
	
	(method (init)
		(= signal (| signal $1000))
		(super init: &rest)
		(oNotInchie init:)
	)
	
	(method (dispose)
		(super dispose:)
		(oNotInchie dispose:)
	)
	
	(method (handleEvent event &tmp temp0 temp1 temp2)
		(if
			(and
				(not local0)
				(<
					(= temp0
						(GetDistance (event x?) (event y?) (+ x 41) (- y 109))
					)
					local1
				)
			)
			(= temp1
				(GetAngle (+ x 41) (- y 109) (event x?) (event y?))
			)
			(= local0 1)
			(-- local1)
			(cond 
				((<= temp1 90) (= temp2 2))
				((<= temp1 180) (= temp2 4))
				((<= temp1 270) (= temp2 3))
				(else (= temp2 2))
			)
			(self loop: temp2 setCycle: End coWormPoppedUp)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		(theGame handsOff:)
		(++ local2)
		(messager
			isVisible:
			say: noun theVerb 0 local2 (ScriptID 64020 0)
		)
		(if (> local2 3)
			(ego get: ((ScriptID 64001 0) get: 3))
			((ScriptID 64017 0) set: 3)
			(self dispose:)
		)
	)
	
	(method (onMe theObjOrX)
		(if
		(not (oNotInchie onMe: (theObjOrX x?) (theObjOrX y?)))
			(super onMe: theObjOrX)
		)
	)
)

(instance coWormPoppedUp of CueMe
	(properties)
	
	(method (cue)
		(theGame handsOn:)
		(poInchworm cel: 0 loop: 1 cycleSpeed: 9 setCycle: Fwd)
		(= local0 0)
	)
)

(instance foExit of CUExitFeature
	(properties
		unregisterDefaultHandler 11100
	)
)

(instance roSewingBasket of TPRoom
	(properties
		picture 11200
	)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: 11200)
		(= local1 70)
		(foExit init:)
		(theGame handsOff:)
		(poInchworm
			loop: 0
			cel: 0
			init:
			setVisibleRange: 1
			setCycle: End coWormPoppedUp
		)
		(= local2 0)
	)
)
