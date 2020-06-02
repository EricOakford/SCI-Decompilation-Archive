;;; Sierra Script 1.0 - (do not remove this comment)
(script# 98)
(include game.sh) (include "98.shm")
(use Main)
(use Procs)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	Dummy98_0 0
	Dor1 1
	Dor2 2
	Dor6 3
	lttleButton 5
	bhindDoor11 8
	theTrap1 13
	theTrap2 14
	theTrap3 15
	theFrontTrap 16
	Dummy98_18 18
	bhindDoor7 19
	dor7 20
	arow 21
	dor12 24
	theGlobe 25
	theFly 28
	ball2 30
	theHandle 31
	theHead 32
	Dor15 33
	Chain1 34
	Chain2 35
	PullChain 36
)
;"WARNING: Invalid function offset: f000" when attempting to decompile these procedures. Fortunately, they don't seem to be used at all.
(procedure (Dummy98_0)
)

(procedure (Dummy98_18)
)

(instance Dor1 of Feature
	(properties
		noun N_DOOR
		modNum 98
		nsTop 47
		nsLeft 2
		nsBottom 104
		nsRight 18
	)
)

(instance Dor15 of Feature
	(properties
		noun N_DOOR
		modNum 98
		nsTop 45
		nsLeft 300
		nsBottom 105
		nsRight 318
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Chain1 of Feature
	(properties
		noun N_CHAIN
		modNum 98
		nsTop 49
		nsLeft 281
		nsBottom 84
		nsRight 294
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Chain2 of Feature
	(properties
		noun N_CHAIN
		modNum 98
		nsTop 51
		nsLeft 23
		nsBottom 85
		nsRight 37
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Dor2 of Feature
	(properties
		noun N_DOOR2
		modNum 98
		nsTop 1
		nsLeft 49
		nsBottom 51
		nsRight 64
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Dor6 of Feature
	(properties
		noun N_DOOR
		modNum 98
		nsTop 22
		nsLeft 87
		nsBottom 71
		nsRight 102
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lttleButton of Feature
	(properties
		noun N_LITTLEBUTTON
		modNum 98
		nsTop 59
		nsLeft 50
		nsBottom 87
		nsRight 66
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bhindDoor11 of View
	(properties
		x 211
		y 62
		noun N_BEHINDDOOR11
		modNum 98
		view 291
		loop 11
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theTrap1 of Prop
	(properties
		x 46
		y 93
		noun N_TRAPDOOR
		modNum 98
		view 296
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theTrap2 of Prop
	(properties
		x 305
		y 148
		noun N_TRAPDOOR
		modNum 98
		view 296
		loop 1
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theTrap3 of Prop
	(properties
		x 107
		y 125
		noun N_TRAPDOOR
		modNum 98
		view 296
		loop 2
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theFrontTrap of Prop
	(properties
		x 305
		y 148
		noun N_TRAPDOOR
		modNum 98
		view 296
		loop 10
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bhindDoor7 of Prop
	(properties
		x 111
		y 66
		view 96
		loop 2
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
			(curRoom doVerb: theVerb &rest)
			(return TRUE)
		)
		(return
			(switch theVerb
				(4
					(if (== (ego onControl: 1) 4096)
						(if (Btst 264) (ego setScript: (ScriptID 96 1)))
					else
						(messager say: N_BEHINDDOOR7 V_DO 0 0 0 98)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance dor7 of Prop
	(properties
		x 133
		y 68
		noun N_BEHINDDOOR7
		modNum 98
		view 96
		loop 1
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
			(curRoom doVerb: theVerb &rest)
			(return TRUE)
		)
		(return
			(switch theVerb
				(4
					(if (== (ego onControl: 1) 4096)
						(if (Btst 264)
							(ego setScript: (ScriptID 96 1))
						else
							(dor7 setScript: (ScriptID 96 2))
						)
					else
						(messager say: N_BEHINDDOOR7 V_DO 0 0 0 98)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance arow of Prop
	(properties
		x 160
		y 62
		noun N_ARROW
		modNum 98
		view 96
		loop 3
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance dor12 of Prop
	(properties
		x 260
		y 58
		noun N_DOOR12
		modNum 98
		view 290
		loop 6
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theGlobe of Prop
	(properties
		x 156
		y 1
		noun N_GLOBE
		modNum 98
		view 291
		loop 8
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theFly of Actor
	(properties
		view 291
		loop 9
	)
)

(instance ball2 of Actor
	(properties
		view 291
		loop 8
	)
)

(instance theHandle of Feature
	(properties
		x 238
		y 85
		nsTop 77
		nsLeft 212
		nsBottom 98
		nsRight 267
		onMeCheck $0100
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (ego script?) (ScriptID 96 3))
				(< 69 (ego y?))
				(< (ego y?) 99)
				(< ((ScriptID 96 3) state?) 6)
			)
			((ScriptID 96 3) changeState: 6)
		)
		(event claimed: TRUE)
	)
)

(instance theHead of Prop
	(properties
		x 157
		y 50
		view 297
		loop 6
		cel 1
	)
	
	(method (doit &tmp temp0)
		(= temp0
			(GetAngle
				((ScriptID 96 5) x?)
				((ScriptID 96 5) y?)
				(ego x?)
				(ego y?)
			)
		)
		(if
		(and (== (theHead loop?) 6) (!= (theHead cel?) 3))
			(cond 
				((< temp0 135) (if (!= (theHead cel?) 2) (theHead setCel: 2)))
				((< temp0 225) (if (!= (theHead cel?) 1) (theHead setCel: 1)))
				((!= (theHead cel?) 0) (theHead setCel: 0))
			)
		)
		(super doit:)
	)
)

(instance PullChain of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 298 posn: 57 47 setLoop: 2 setCel: 0)
				(= ticks 30)
			)
			(1 (ego setCycle: CycleTo 2 1 self))
			(2 (= ticks 12))
			(3
				(ego setCel: 3)
				((ScriptID 96 6) setCel: 1)
				(= ticks 12)
			)
			(4
				(ego setCel: 0)
				((ScriptID 96 6) setCel: 0)
				(= ticks 12)
			)
			(5
				(NormalEgo)
				(ego loop: 0)
				(if (not (Btst 259))
					(Bset 259)
					(theHead dispose:)
					((ScriptID 96 5) setLoop: 5)
				)
				(if (not (client cel?))
					(client setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(6 (HandsOn) (self dispose:))
		)
	)
)
