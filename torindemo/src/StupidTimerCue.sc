;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51200)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use TPSound)
(use Script)
(use CueMe)
(use ExitFeature)
(use Polygon)
(use Feature)
(use Timer)
(use Motion)
(use Actor)

(public
	roLycentiasPorch 0
)

(local
	local0
	local1
)
(instance foNullVoidExit of ExitFeature
	(properties)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 485 187 407 319 633 318 634 -2 492 -2 485 44 570 96 516 203
					yourself:
				)
		)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 7))
	)
	
	(method (doVerb)
		(curRoom setScript: soExit)
	)
)

(instance foInside of Feature
	(properties
		x 344
		y 203
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 348 115 269 249 265 277 332 291 424 153
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1) (curRoom setScript: soGoInside))
	)
)

(instance foDoorbell of Feature
	(properties
		x 295
		y 162
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 286 155 286 170 304 170 304 155
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1) (curRoom setScript: soRingDoorbell))
	)
)

(instance foDoorstep of Feature
	(properties
		sightAngle 40
		x 430
		y 136
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 348 113 422 151 484 160 512 154 424 123
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(if (and (== theVerb 1) (not local0))
			(curRoom setScript: soOnPorch)
		)
	)
)

(instance foDoorbottom of Feature
	(properties
		sightAngle 40
		x 453
		y 75
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 373 73 431 87 513 121 533 104 386 29
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(if (and (== theVerb 1) local0)
			(curRoom setScript: soUnderPorch)
		)
	)
)

(instance poTorinStop of Prop
	(properties
		view -14335
	)
)

(instance poTorin of Prop
	(properties
		view -14335
	)
)

(instance poDreep of Prop
	(properties
		x 361
		y 113
		view -14335
	)
)

(instance poDoor of Prop
	(properties
		x 361
		y 251
		view -14335
		loop 10
	)
)

(instance oDoorBell of TPSound
	(properties)
)

(instance soGoInside of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not local0)
					(self setScript: soOnPorch self)
				else
					(self cue:)
				)
			)
			(1
				(poTorinStop hide:)
				(poTorin
					posn: 465 108
					loop: 9
					cel: 0
					init:
					setCycle: End self
				)
			)
			(2
				((ScriptID 56000 0) BAD_SELECTOR:)
				(curRoom newRoom: -14236)
				(self dispose:)
			)
		)
	)
)

(instance soOnPorch of Script
	(properties)
	
	(method (dispose)
		(poTorinStop loop: 11 show:)
		(theGame handsOn:)
		(poTorin dispose:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local0 1)
				(poTorinStop hide:)
				(poTorin
					posn: 465 108
					loop: 11
					cel: 0
					init:
					setCycle: End self
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance soUnderPorch of Script
	(properties)
	
	(method (dispose)
		(poTorinStop loop: 12 show:)
		(theGame handsOn:)
		(poTorin dispose:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local0 0)
				(poTorinStop hide:)
				(poTorin
					posn: 465 108
					loop: 2
					cel: 0
					init:
					setCycle: End self
				)
			)
			(1 (self dispose:))
		)
	)
)

(class StupidTimerCue of CueMe
	(properties
		scratch 0
	)
	
	(method (cue)
		(oStupidProp setScript: soTimesUp)
	)
	
	(method (timer)
	)
)

(instance oStupidTimer of Timer
	(properties)
)

(instance oStupidProp of Prop
	(properties)
)

(instance soRingDoorbell of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not local0)
					(self setScript: soOnPorch self)
				else
					(self cue:)
				)
			)
			(1
				(oDoorBell lThumbLoop: -14335)
				(poTorinStop hide:)
				(poTorin
					posn: 465 108
					loop: 1
					cel: 0
					init:
					setCycle: End self
				)
			)
			(2
				(theGame handsOn:)
				(MonoOut {Quick, hide!})
				(if (not local1)
					(= local1 1)
					(oStupidTimer setReal: StupidTimerCue 5)
				)
				(poTorinStop show:)
				(poTorin dispose:)
				(self dispose:)
			)
		)
	)
)

(instance soTimesUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local1 0)
				(MonoOut {Times up})
				(theGame handsOff:)
				(poDoor cel: 0 init: setCycle: End self)
			)
			(1
				(cond 
					(local0 (self setScript: soDreepEatTorin self))
					((ego has: ((ScriptID 64001 0) get: 52)) (self setScript: soDreepLook self))
					(else (self setScript: soDreepLeave self))
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soDreepLook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 0 0 2 0 self)
				(poDreep
					posn: 361 113
					loop: 5
					cel: 0
					init:
					setCycle: End self
				)
			)
			(1)
			(2 (poDoor setCycle: Beg self))
			(3
				(poDreep dispose:)
				(self dispose:)
			)
		)
	)
)

(instance soDreepLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 0 0 3 0 self)
				(poDreep
					posn: 361 113
					loop: 6
					cel: 0
					init:
					setCycle: End self
				)
			)
			(1)
			(2
				((ScriptID 64017 0) set: 141)
				(foInside init:)
				(poDreep dispose:)
				(self dispose:)
			)
		)
	)
)

(instance soDreepEatTorin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poDreep
					posn: 361 113
					loop: 4
					cel: 0
					init:
					setCycle: CT 5 1 self
				)
			)
			(1
				(messager say: 0 0 1 0 self)
				(poTorinStop hide:)
				(poDreep setCycle: End self)
			)
			(2)
			(3
				(theGame handsOn:)
				(if ((ScriptID 64019 0) show:)
					(poDoor setCycle: Beg self)
				)
			)
			(4
				(poDreep dispose:)
				(poTorinStop show:)
				(self dispose:)
			)
		)
	)
)

(instance soExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(poTorinStop dispose:)
				(poTorin
					posn: 465 108
					loop: 8
					cel: 0
					init:
					setCycle: End self
				)
			)
			(1
				(poTorin dispose:)
				(curRoom newRoom: -14436)
				(self dispose:)
			)
		)
	)
)

(instance soEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poTorin
					posn: 465 108
					loop: 0
					cel: 0
					init:
					setCycle: End self
				)
			)
			(1
				(poTorinStop show:)
				(poTorin dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance roLycentiasPorch of TPRoom
	(properties
		picture -14336
	)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: -14436)
		(foNullVoidExit init:)
		(foDoorstep init:)
		(foDoorbottom init:)
		(foDoorbell init:)
		(poDoor init:)
		(= local0 1)
		(= local1 0)
		(poTorinStop
			view: -14335
			loop: 11
			posn: 465 108
			init:
			hide:
		)
		(curRoom setScript: soEnter)
	)
	
	(method (dispose)
		(oStupidTimer delete:)
		(super dispose:)
	)
	
	(method (setWander param1)
		(return
			(cond 
				((== param1 -14236) (return foInside))
				((== param1 -14436) (return foNullVoidExit))
			)
		)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 52))
	)
)
