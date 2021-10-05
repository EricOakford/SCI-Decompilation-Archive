;;; Sierra Script 1.0 - (do not remove this comment)
(script# 52)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Reverse)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm52 0
)

(local
	underwaterTimer
	local1
)
(procedure (LookWeed)
	(cond 
		((== (ego view?) 14)
			(Print 52 27)
		)
		(
			(and
				(< 159 (ego x?))
				(< (ego x?) 252)
				(> (ego y?) 125)
			)
			(Print 52 41)
		)
		(else
			(Print 52 26)
		)
	)
)

(instance rm52 of Room
	(properties
		picture 52
		north 49
		west 51
	)
	
	(method (init)
		(LoadMany VIEW 1 6 9 13 14 252 68)
		(LoadMany SOUND 72 96 14 64)
		(self style:
			(switch prevRoomNum
				(north DISSOLVE)
				(east WIPELEFT)
			)
		)
		(weed1 setCycle: Forward setPri: 0 init: ignoreActors:)
		(weed2 setCycle: Reverse setPri: 0 init: ignoreActors:)
		(weed3 setCycle: Forward setPri: 0 init: ignoreActors:)
		(weed4 setCycle: Reverse init: ignoreActors:)
		(if (not (>= howFast 1))
			(weed1 stopUpd:)
			(weed2 stopUpd:)
			(weed3 stopUpd:)
			(weed4 stopUpd:)
		)
		(super init:)
		(addToPics add: trunk eachElementDo: #init doit:)
		(switch prevRoomNum
			(west
				(ego posn: 14 58 illegalBits: -32512 loop: 0)
				(NormalEgo)
			)
			(else 
				(ego
					ignoreActors:
					illegalBits: cWHITE
					posn: 204 20
					setMotion: MoveTo 204 130
					setCycle: Forward
					setPri: -1
					view: 6
				)
				(= egoInWater 4)
				(= underwaterTimer 300)
				((ScriptID 0 23) number: 72 loop: -1 play:)
			)
		)
		(ego init:)
	)
	
	(method (doit &tmp nRoom)
		(if
		(and (== egoInWater 4) swimTimer (not (-- swimTimer)))
			(curRoom setScript: drowning)
		)
		(if (== (ego view?) 6)
			(if
				(or
					(< (ego y?) 148)
					(> (ego y?) 158)
					(< (ego x?) 140)
				)
				(ego illegalBits: cWHITE)
			else
				(ego illegalBits: 0)
			)
		)
		(cond 
			(script
				(script doit:)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				((ScriptID 0 23) fade:)
				(self newRoom: nRoom)
			)
			((& (ego onControl: origin) cYELLOW)
				(curRoom setScript: upToSurface)
			)
			(
				(and
					(or (== (ego view?) 0) (== (ego view?) 36))
					(& (ego onControl: origin) cLGREEN)
					(not isHandsOff)
				)
				(curRoom setScript: fallInWater)
			)
			(underwaterTimer
				(-- underwaterTimer)
				(if (== (ego view?) 6)
					(switch (ego loop?)
						(3 (ego setStep: 1 3))
						(2 (ego setStep: 1 1))
						(0 (ego setStep: 3 1))
						(1 (ego setStep: 3 1))
					)
				)
			)
			((== (ego view?) 6)
				(curRoom setScript: drownEgo)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'smell,sniff/')
				(if (== (ego view?) 6)
					(Print 52 0)
					(curRoom setScript: drownEgo)
				else
					(event claimed: FALSE)
				)
			)
			((Said 'dive')
				(switch (ego view?)
					(14
						(Print 52 1)
					)
					(6
						(Print 52 2)
					)
					(13
						(curRoom setScript: goDiving)
					)
					(else
						(Print 52 3)
					)
				)
			)
			((Said 'swim<cease')
				(switch (ego view?)
					(14
						(Print 52 4)
					)
					(13
						(Print 52 5)
					)
					(else
						(Print 52 6)
					)
				)
			)
			((Said 'swim')
				(switch (ego view?)
					(13
						(Print 52 7)
					)
					(14
						(= egoInWater 4)
						((ScriptID 0 21) number: 51 loop: -1 init: play:)
						(ego
							show:
							view: 13
							illegalBits: -24576
							setStep: 1 1
							setCycle: Forward
							setLoop: -1
							setCel: -1
							setPri: -1
							cycleSpeed: 0
						)
						(= swimTimer 3000)
						(curRoom setScript: 0)
						(HandsOn)
					)
					(6 (Print 52 2))
					(else 
						(curRoom setScript: goSwimming)
					)
				)
			)
			((Said 'climb,scale[<in]/water')
				(if (not egoInWater)
					(Print 52 8)
				else
					(Print 52 9)
				)
			)
			((or (Said 'climb,scale,get,take<out') (Said 'exit/water'))
				(switch (ego view?)
					(14
						(Print 52 10)
					)
					(6
						(Print 52 11)
					)
					(13
						(curRoom setScript: getOutaWater)
					)
					(else
						(Print 52 3)
					)
				)
			)
			((Said 'climb,scale[/!*]')
				(Print 52 12)
			)
			((Said 'get,take/chest')
				(Print 52 13)
			)
			((Said 'enter,(get,take,jump<in)/water')
				(Print 52 14)
			)
			((Said 'enter,(get,take,jump<in)')
				(Print 52 15)
			)
			((or (Said 'get,take/water') (Said 'fill/bucket'))
				(cond 
					((== (ego view?) 14)
						(Print 52 16)
					)
					(
						(and
							(not (& (ego onControl: origin) cLMAGENTA))
							(not (OneOf (ego view?) 6 13))
						)
						(Print 52 17)
					)
					((ego has: iWaterBucket)
						(if (Btst fWaterInBucket)
							(Print 52 18)
						else
							(if (OneOf (ego view?) 6 13)
								(Print 52 19)
							else
								(self setScript: fillBucket)
							)
							(BucketState TRUE)
						)
					)
					(else
						(Print 52 20)
					)
				)
			)
			((or (Said 'get,take/drink') (Said 'drink[/water]'))
				(cond 
					((== (ego view?) 14)
						(Print 52 21)
					)
					(
						(and
							(not (& (ego onControl: origin) cLMAGENTA))
							(not (OneOf (ego view?) 6 13))
						)
						(if (and (ego has: iWaterBucket) (Btst fWaterInBucket))
							(Print 52 22)
							(BucketState 0)
						else
							(Print 52 17)
						)
					)
					(else
						(Print 52 23)
					)
				)
			)
			((Said '/hinge')
				(Print 52 24)
			)
			((or (Said 'look,check/hole') (MouseClaimed event 47 48 99 62))
				(if (< (ego x?) 128) (Print 52 25) else (Print 52 26))
			)
			((Said 'look,check>')
				(cond 
					((Said '<up')
						(cond 
							((== (ego view?) 14)
								(Print 52 27)
							)
							((and (== (ego view?) 6) (ego inRect: 19 105 136 187))
								(Print 52 28)
							)
							((== (ego view?) 6)
								(Print 52 29)
							)
							(else
								(Print 52 30)
							)
						)
					)
					((Said '[<at,around][/room,well,cave]')
						(switch (ego view?)
							(6
								(if (< (ego y?) 118)
									(Print 52 31)
								else
									(Print 52 32)
								)
							)
							(else
								(Print 52 33)
							)
						)
					)
					((Said '/water')
						(Print 52 34)
					)
				)
			)
		)
	)
)

(instance weed1 of Prop
	(properties
		x 220
		y 133
		description {weed}
		view 252
		cel 4
		cycleSpeed 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(LookWeed)
			)
		)
	)
)

(instance weed2 of Prop
	(properties
		x 149
		y 157
		description {weed}
		view 252
		loop 1
		cel 1
		cycleSpeed 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(LookWeed)
			)
		)
	)
)

(instance weed3 of Prop
	(properties
		x 264
		y 140
		description {weed}
		view 252
		loop 1
		cel 2
		cycleSpeed 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(LookWeed)
			)
		)
	)
)

(instance weed4 of Prop
	(properties
		x 187
		y 163
		description {weed}
		view 252
		cel 3
		cycleSpeed 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(LookWeed)
			)
		)
	)
)

(instance trunk of PicView
	(properties
		x 194
		y 152
		noun 'chest'
		description {chest}
		view 252
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'look,check/chest')
				(self doVerb: verbLook event)
			)
			((or (Said 'look,check<down') (Said 'look,check/floor'))
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbGet
				(Print 52 35)
			)
			(verbOpen
				(Print 52 36)
			)
			(else 
				(cond 
					((== (ego view?) 14)
						(Print 52 27)
					)
					((ego has: iChest) ((inventory at: iChest) showSelf: ego)
						(Print 52 37)
					)
					(
						(or
							(and
								(< 159 (ego x?))
								(< (ego x?) 252)
								(> (ego y?) 125)
							)
							(< argc 1)
						)
						(Print 52 38)
					)
					(else
						(Print 52 26)
					)
				)
			)
		)
	)
)

(instance upToSurface of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego hide:)
				(= cycles 9)
			)
			(1
				(= egoInWater 4)
				(= swimTimer 3000)
				(ego
					show:
					posn: 73 57
					illegalBits: -24576
					setStep: 1 1
					view: 13
					setCycle: Forward
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getOutaWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21) loop: 1 stop:)
				((ScriptID 0 23) loop: 1 fade:)
				(ego
					illegalBits: 0
					setLoop: 1
					setMotion: MoveTo 65 58 self
				)
			)
			(1
				(ego
					view: 9
					cel: 0
					setLoop: 0
					cycleSpeed: 1
					posn: 43 55
					setCycle: EndLoop self
				)
			)
			(2
				(= egoInWater 0)
				(NormalEgo)
				(ego
					loop: 1
					illegalBits: -32512
					setMotion: MoveTo 32 54 self
				)
			)
			(3
				(if (and (not (Btst fDragonDead)) (not (Btst fDragonDoused)))
					((ScriptID 0 23) number: 64 loop: -1 play:)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance drownEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego cycleSpeed: 1 setMotion: 0)
				(= cycles 14)
			)
			(1
				(HandsOff)
				(ego
					setLoop: (if (ego loop?) 5 else 4)
					setCycle: 0
					setCel: 0
				)
				(= cycles 4)
			)
			(2
				(ego
					setStep: 1 1
					moveSpeed: 1
					setMotion: MoveTo (ego x?) 200
				)
				(= seconds 9)
			)
			(3
				(EgoDead {Well, well, well!__Water you going to do now?})
			)
		)
	)
)

(instance goSwimming of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 43 55 self)
			)
			(1
				(if (Btst fInvisible)
					(Print 52 39)
					(Bclr fInvisible)
					(Bclr fWearingRing)
					(PutInRoom iMagicRing)
					(theGame changeScore: -3)
					(self cue:)
				else
					(self cue:)
				)
			)
			(2
				(= swimTimer 3000)
				(= egoInWater 4)
				(ego view: 9 setLoop: 0 cel: 6 setCycle: BegLoop self)
			)
			(3
				(ego
					view: 13
					setLoop: -1
					setCycle: Forward
					illegalBits: -24576
					posn: 72 59
					setMotion: MoveTo 73 57 self
				)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goDiving of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21) loop: 1 fade:)
				(ego view: 14 cel: 8 setLoop: 0 setCycle: EndLoop self)
			)
			(1 (= cycles 6))
			(2
				((ScriptID 0 23) number: 72 loop: -1 play:)
				(ego
					show:
					setLoop: -1
					loop: 2
					ignoreActors: 1
					posn: 70 130
					setMotion: MoveTo 70 184
					looper: 0
					setCycle: Forward
					view: 6
				)
				(= egoInWater 4)
				(= underwaterTimer 300)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance fallInWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 23) loop: -1 fade:)
				(ego
					view: 14
					setLoop: 1
					cel: 0
					setCycle: 0
					cycleSpeed: 0
					setMotion: JumpTo 77 55 self
				)
				(= egoInWater 4)
			)
			(1
				((ScriptID 0 21) number: 14 loop: 1 init: play:)
				(ego cel: 1 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(2
				(= egoInWater 4)
				((ScriptID 0 21) number: 51 loop: -1 play:)
				(ego
					show:
					view: 13
					illegalBits: -24576
					setStep: 1 1
					setCycle: Forward
					setLoop: -1
					setCel: -1
					setPri: -1
					cycleSpeed: 0
				)
				(= swimTimer 3000)
				(curRoom setScript: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event)
				(return)
			)
			((Said 'swim')
				(HandsOn)
				((ScriptID 0 23) stop:)
				(ego
					illegalBits: -24576
					view: 13
					setLoop: -1
					cycleSpeed: 0
					setCycle: Forward
				)
				(= seconds 0)
				(self dispose:)
			)
		)
	)
)

(instance drowning of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: 0)
				(ego
					setMotion: 0
					setLoop: 0
					view: 14
					cel: 5
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(ego cel: 5 setCycle: EndLoop self)
			)
			(2
				(ego cel: 5 setCycle: EndLoop self)
			)
			(3
				(ego hide:)
				(= cycles 40)
			)
			(4
				(ego
					view: 6
					loop: (Random 4 5)
					posn: (ego x?) 120
					show:
					setStep: 1 1
					illegalBits: 0
					moveSpeed: 1
					setMotion: MoveTo (ego x?) 155 self
				)
			)
			(5
				(if (not swimTimer)
					(EgoDead
						{After swimming for a long time, your strength ebbs and your arms and legs grow weary.__As your life swims before your eyes, you decide to...}
					)
				else
					(EgoDead
						{You splash around for awhile, but unfortunately that won't keep your head above water.__As you go down for the third time, a sense of peace washes over you...}
					)
				)
			)
		)
	)
)

(instance fillBucket of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 41 54 self)
			)
			(1
				(ego view: 68 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(2
				(Print 52 40)
				(self cue:)
			)
			(3
				(ego setCycle: BegLoop self)
			)
			(4
				(ego view: 2 loop: 0)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
