;;; Sierra Script 1.0 - (do not remove this comment)
(script# 91)
(include game.sh) (include "91.shm")
(use Main)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	streetRgn 0
	streetSounds 1
)

(local
	lookedLeft
	lookedRight
	local2
	local3
)
(instance streetRgn of Region
	
	(method (init)
		(Load RES_VIEW 853)
		(LoadMany RES_SOUND 96 94 81)
		(leftFeature init:)
		(rightFeature init:)
		(car setLoop: 3 setCel: 3 xStep: 6 moveSpeed: 3 hide:)
		(if (not (streetSounds handle?))
			(streetSounds play:)
		)
		(super init:)
	)
	
	(method (doit)
		(cond 
			((curRoom script?))
			(
				(and
					(== lookedLeft TRUE)
					(== lookedRight TRUE)
					(IsObjectOnControl ego cGREEN)
				)
				(curRoom setScript: sLeaveNow)
			)
			(
				(and
					(not (if (== lookedLeft TRUE) (== lookedRight TRUE)))
					(IsObjectOnControl ego cGREEN)
				)
				(curRoom setScript: sRunOver)
			)
			((IsObjectOnControl ego cGREY)
				(curRoom setScript: sHitEdgeScreen)
			)
		)
		(super doit:)
	)
	
	(method (newRoom n)
		(= keep (OneOf n 280 210 260 300))
		(= initialized 0)
	)
)

(instance sLeaveNow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego heading: 180)
				(= cycles 2)
			)
			(1
				(ego setMotion: MoveFwd 65 self)
			)
			(2
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance sRunOver of Script
	
	(method (doit)
		(cond 
			((and (< (car distanceTo: ego) 160) (not local2))
				(mRunOver number: 96 loop: -1 play:)
				(= local2 1)
			)
			((and (< (car distanceTo: ego) 100) (not local3))
				(mRunOver number: 81 loop: 1 play:)
				(= local3 1)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(streetSounds stop:)
				(if (== curRoomNum 280)
					((ScriptID 280 1) dispose:)
					((ScriptID 280 2) setScript: 0)
				)
				(ego setPri: 13)
				(car init:)
				(= cycles 1)
			)
			(1
				(car
					setMotion: MoveTo (+ (ego x?) 55) (- (ego y?) 1) self
				)
			)
			(2
				(switch curRoomNum
					(210
						(car setMotion: MoveTo 160 225)
					)
					(260
						(car setMotion: MoveTo -30 251)
					)
					(280
						(car setMotion: MoveTo 112 221)
					)
					(300
						(car setMotion: MoveTo 220 215)
					)
				)
				(= cycles 1)
			)
			(3
				(ego
					view: 853
					loop: (if (== (ego view?) 803) 1 else 0)
					cel: 0
					posn: (- (ego x?) 19) (- (ego y?) 1)
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(4
				(= seconds 7)
				(if (ego looper?)
					(ego setLoop: 0)
				)
			)
			(5
				(= deathReason deathRUNOVER)
				(curRoom newRoom: DEATH)
			)
		)
	)
)

(instance sHitEdgeScreen of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: N_EDGE V_WALK NULL 0 self 91)
			)
			(1
				(if (> (ego heading?) 180)
					(ego setHeading: 90)
				else
					(ego setHeading: 270)
				)
				(ego setMotion: MoveFwd 10 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance leftFeature of Feature
	(properties
		x 28
		y 100
		nsTop 88
		nsBottom 189
		nsRight 20
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(= lookedLeft TRUE)
				(messager say: N_LEFT V_LOOK NULL 0 0 91)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance rightFeature of Feature
	(properties
		x 287
		y 100
		nsTop 88
		nsLeft 300
		nsBottom 189
		nsRight 320
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(= lookedRight TRUE)
				(messager say: N_RIGHT V_LOOK NULL 0 0 91)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance car of Actor
	(properties
		x 362
		y 181
		view 213
		loop 3
		cel 3
		signal ignrAct
		moveSpeed 3
	)
)

(instance mRunOver of Sound
	(properties
		flags mNOPAUSE
	)
)

(instance streetSounds of Sound
	(properties
		flags mNOPAUSE
		number 94
		loop -1
	)
)
