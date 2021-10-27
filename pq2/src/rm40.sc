;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40)
(include sci.sh)
(use Main)
(use jet)
(use Intrface)
(use Wander)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm40 0
)

(local
	local0
	local1
	local2
	triedToLeave
	triedToEnterBathroom
	local5
	alcoholicDrinksConsumed
	local7
	local8
	askedForOrder
	local10
	guard
	cockpitDoor
	[str 100]
)
(procedure (localproc_0036 param1 param2 &tmp temp0)
	(while
	(not (!= (= temp0 (Random param1 param2)) local10))
	)
	(= local10 temp0)
	(return temp0)
)

(instance planeRumble of Sound
	(properties
		number 46
		loop 2
	)
)

(instance rm40 of Room
	(properties
		picture 40
		style $0000
	)
	
	(method (init)
		(= perspective 70)
		(Load rsVIEW 0)
		(Load rsVIEW 82)
		(Load rsVIEW 26)
		(Load rsVIEW 31)
		(Load rsVIEW 20)
		(Load rsSOUND 46)
		(self setLocales: 154)
		(super init:)
		(ego
			view: 0
			setStep: 3 2
			posn: 336 82
			loop: 1
			illegalBits: 0
			init:
		)
		((= keith (Actor new:))
			view: 20
			ignoreActors:
			illegalBits: 0
			posn: 324 72
			setCycle: Walk
			init:
		)
		((= stewardess (Actor new:))
			view: 26
			posn: 255 73
			loop: 1
			cel: 0
			setCycle: Walk
			illegalBits: 0
			init:
		)
		((= guard (Actor new:))
			view: 31
			posn: 300 1063
			loop: 1
			cel: 0
			setCycle: Walk
			illegalBits: 0
			ignoreActors:
			init:
		)
		((= cockpitDoor (Prop new:))
			view: 82
			posn: 271 55
			loop: 8
			cel: 0
			setPri: 0
			ignoreActors:
			init:
			cycleSpeed: 1
			stopUpd:
		)
		((Prop new:)
			view: 82
			posn: 51 191
			loop: 2
			cel: 0
			setPri: 15
			addToPic:
		)
		(GoToBathroom)
		(= sittingInPlane 0)
		(= wearingSeatbelt 0)
		(= alcoholicDrinksConsumed 0)
		(self setScript: initScript)
	)
	
	(method (doit)
		(if
		(and (& (ego onControl:) $4000) (not isHandsOff))
			(if (not triedToLeave) (= triedToLeave 1) (Print 40 0))
		else
			(= triedToLeave 0)
		)
		(cond 
			((not (ego inRect: 50 156 92 162)) (= triedToEnterBathroom 0))
			((not triedToEnterBathroom) (Print 40 1) (= triedToEnterBathroom 1))
		)
		(super doit:)
	)
	
	(method (dispose)
		(stage2Timer dispose: delete:)
		(super dispose:)
	)
)

(instance initScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 285 70 self)
				(keith setMotion: MoveTo 280 62)
				(stewardess loop: 0)
			)
			(1
				(AirplanePrint 40 2)
				(= seconds 2)
			)
			(2
				(stewardess loop: 2)
				(HandsOn)
				(ego illegalBits: -32768)
				(keith setMotion: MoveTo 233 60 self)
			)
			(3
				(keith setMotion: MoveTo 224 57 self)
			)
			(4
				(keith
					view: 82
					setLoop: 4
					setCel: 0
					illegalBits: 0
					ignoreActors:
					setPri: 0
					posn: 200 68
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(5
				(curRoom setScript: StageOne)
			)
		)
	)
)

(instance StageOne of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 40))
			(1 (= seconds 5))
			(2
				(AirplanePrint 40 3)
				(AirplanePrint 40 4)
				(if (not wearingSeatbelt)
					(= seconds 20)
				else
					(self changeState: 20)
				)
			)
			(3
				(HandsOff)
				(if (& (ego onControl:) $1800)
					(self cue:)
				else
					(stewardess setMotion: MoveTo 232 72 self)
				)
			)
			(4
				(stewardess setMotion: Chase ego 20 self)
			)
			(5
				(= local2 1)
				(AirplanePrint
					(Format
						@str
						40
						5
						(if sittingInPlane {_} else { find your seat and_})
					)
					67
					10
					15
					33
					smallFont
				)
				(HandsOn)
				(if sittingInPlane (User canControl: 0))
				(stewardess setMotion: MoveTo 232 72 self)
			)
			(6
				(stewardess setMotion: MoveTo 270 70 self)
			)
			(7
				(stewardess loop: 2 cel: 4)
				(= seconds 30)
			)
			(8
				(guard setScript: guardAction)
			)
			(20 (= seconds 5))
			(21 (self changeState: 25))
			(25
				(stewardess setScript: stewardessActions)
				(stewardessActions changeState: 1)
				(= cycles 25)
			)
			(26
				(AirplanePrint 40 6)
				(if (>= gamePhase 13)
					(= seconds 10)
				else
					(curRoom setScript: WildGooseChase)
				)
			)
			(27
				(AirplanePrint 40 7)
				(= seconds 10)
				(planeRumble play:)
			)
			(28
				(stewardess setScript: stewardessActions)
				(stewardessActions changeState: 6)
				(= cycles 20)
			)
			(29
				(curRoom setScript: StageTwo)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'look/pane') (AirplanePrint 40 8))
					((Said 'sat')
						(cond 
							(sittingInPlane (event claimed: 0))
							((not (ego inRect: 210 56 239 64)) (AirplanePrint 40 9))
							(else
								(if (and (== state 0) (> seconds 2)) (= seconds 2))
								(ego setScript: egoSit)
							)
						)
					)
					((Said 'chat/friend')
						(if sittingInPlane
							(AirplanePrint 40 10)
						else
							(AirplanePrint 40 11)
						)
					)
					((Said 'open/door')
						(if (& (ego onControl:) $2000)
							(Print 40 12)
						else
							(event claimed: 0)
						)
					)
					((Said 'knock,beat/door')
						(if (& (ego onControl:) $2000)
							(AirplanePrint 40 13)
						else
							(NotClose)
						)
					)
					((Said 'chat/attendant')
						(cond 
							((> (ego distanceTo: stewardess) 25) (NotClose))
							(local1 (AirplanePrint 40 14))
							(sittingInPlane (AirplanePrint 40 15))
							(else (AirplanePrint 40 16))
						)
					)
					(
					(Said 'unfasten,unbuckle,remove,(get<off)/belt,belt')
						(if wearingSeatbelt
							(Print 40 17)
						else
							(event claimed: 0)
						)
					)
					((Said 'fasten,deposit,wear,buckle/belt,belt')
						(if (and sittingInPlane (not wearingSeatbelt))
							(= wearingSeatbelt 1)
							(SolvePuzzle 1 163)
							(Print 40 18)
							(if (< state 2)
								(self changeState: 1)
							else
								(self changeState: 20)
							)
						else
							(event claimed: 0)
						)
					)
					((Said 'meditate,nap') (if sittingInPlane (Print 40 19) else (Print 40 20)))
				)
			)
		)
	)
)

(instance stewardessActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(stewardess setMotion: MoveTo 268 60 self)
			)
			(2
				(cockpitDoor startUpd: setCycle: EndLoop self)
			)
			(3
				(cockpitDoor stopUpd:)
				(stewardess setPri: 0 setMotion: MoveTo 280 50 self)
			)
			(4
				(cockpitDoor startUpd: setCycle: BegLoop self)
				(stewardess hide:)
			)
			(5
				(cockpitDoor stopUpd:)
				(client setScript: 0)
			)
			(6
				(cockpitDoor setCycle: EndLoop self)
			)
			(7
				(cockpitDoor stopUpd:)
				(stewardess
					show:
					startUpd:
					setPri: 0
					setMotion: MoveTo 268 55 self
				)
			)
			(8
				(stewardess setPri: -1)
				(cockpitDoor setCycle: BegLoop self)
			)
			(9
				(cockpitDoor stopUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance egoSit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= sittingInPlane 1)
				(ego
					view: 82
					setLoop: 3
					setCel: 0
					ignoreActors:
					illegalBits: 0
					posn: 212 72
					cycleSpeed: 1
					setMotion: 0
					setPri: 3
					setCycle: EndLoop self
				)
			)
			(1
				(User canInput: 1)
				(ego setScript: 0)
			)
		)
	)
)

(instance guardAction of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local2 0)
				(guard posn: 300 75)
				(if (& (ego onControl:) $1800)
					(guard setMotion: MoveTo 290 75 self)
				else
					(guard setMotion: MoveTo 259 75 self)
				)
			)
			(1
				(if (& (ego onControl:) $1800)
					(self cue:)
				else
					(guard setMotion: MoveTo 232 72 self)
				)
			)
			(2
				(guard setMotion: Chase ego 20 self)
			)
			(3
				(Print 40 21)
				(ego illegalBits: 0 ignoreActors: 1)
				(cond 
					(sittingInPlane (ego setCycle: BegLoop self))
					((& (ego onControl:) $1800) (self changeState: 6))
					(else (self cue:))
				)
			)
			(4
				(if sittingInPlane
					(ego
						view: 0
						posn: 229 59
						loop: 0
						cel: 0
						cycleSpeed: 0
						setPri: -1
					)
				)
				(ego setCycle: Walk setMotion: MoveTo 247 63 self)
				(guard setMotion: Follow ego 10)
			)
			(5
				(ego setMotion: MoveTo 259 53 self)
				(guard setMotion: Follow ego 10)
			)
			(6
				(ego setMotion: MoveTo 320 82 self)
				(guard setMotion: Follow ego 10)
			)
			(7
				(ego dispose:)
				(guard dispose:)
				(EgoDead
					{Sonny, you really must learn not to be such a nuisance. It's not that hard to find your seat and fasten your seatbelt!}
				)
			)
		)
	)
)

(instance WildGooseChase of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(stewardess setScript: stewardessActions)
				(stewardessActions changeState: 6)
				(= cycles 20)
			)
			(2
				(stewardess setMotion: MoveTo 232 72 self)
			)
			(3
				(stewardess loop: 1 cel: 0)
				(RedrawCast)
				(AirplanePrint 40 22)
				(= local0 1)
				(= seconds 8)
			)
			(4 (= state 2) (self cue:))
			(10
				(HandsOff)
				(= local0 0)
				(AirplanePrint 40 23)
				(= seconds 2)
			)
			(11
				(HandsOff)
				(AirplanePrint 40 24)
				(= seconds 2)
			)
			(12
				(AirplanePrint 40 25)
				(= seconds 2)
			)
			(13 (self changeState: 19))
			(18
				(HandsOff)
				(AirplanePrint 40 26)
				(= seconds 2)
			)
			(19
				(= local0 0)
				(= local1 1)
				(AirplanePrint 40 27)
				(= seconds 2)
			)
			(20
				(HandsOff)
				(AirplanePrint 40 28)
				(stewardess setLoop: 3)
				(ego setCycle: BegLoop self)
				(keith setCycle: BegLoop)
			)
			(21
				(ego
					view: 0
					setLoop: -1
					loop: 0
					posn: 229 59
					illegalBits: 0
					cycleSpeed: 0
					setPri: -1
					setCycle: Walk
					setMotion: MoveTo 259 59 self
				)
				(keith
					view: 20
					loop: 0
					cycleSpeed: 0
					posn: 223 57
					illegalBits: 0
					ignoreActors:
					setPri: -1
					setCycle: Walk
				)
				(= sittingInPlane 0)
				(= wearingSeatbelt 0)
			)
			(22
				(ego setMotion: MoveTo 289 65 self)
				(keith setMotion: MoveTo 260 62)
			)
			(23
				(ego setMotion: MoveTo 330 82)
				(keith setMotion: MoveTo 330 80 self)
			)
			(24
				(HandsOn)
				(= global168 1)
				(curRoom newRoom: 20)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'affirmative')
						(if local0
							(WildGooseChase changeState: 18)
						else
							(event claimed: 0)
						)
					)
					((Said 'n')
						(if local0
							(WildGooseChase changeState: 10)
						else
							(event claimed: 0)
						)
					)
				)
			)
		)
	)
)

(instance stage2Timer of Timer
	(properties)
)

(instance StageTwo of Script
	(properties)
	
	(method (init)
		(AirplanePrint 40 29)
		(AirplanePrint 40 30)
		(AirplanePrint 40 31)
		(stage2Timer setCycle: endStageTwo 700)
		(= local8 5)
		(self changeState: 1)
	)
	
	(method (doit)
		(if (and local7 (< state 4))
			(= local7 0)
			(= cycles (= seconds 0))
			(= local8 5)
			(self changeState: 1)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local8 (localproc_0036 0 4))
				(self cue:)
			)
			(1
				(switch local8
					(0
						(stewardess setMotion: MoveTo 180 98 self)
					)
					(1
						(stewardess setMotion: MoveTo 150 113 self)
					)
					(2
						(stewardess setMotion: MoveTo 129 123 self)
					)
					(3
						(stewardess setMotion: MoveTo 90 142 self)
					)
					(else 
						(stewardess setMotion: MoveTo 232 72 self)
					)
				)
			)
			(2
				(switch local8
					(4 (stewardess loop: 0))
					(5
						(stewardess loop: 1)
						(RedrawCast)
						(self changeState: 4)
					)
					(else 
						(stewardess loop: (Random 0 1))
					)
				)
				(= cycles 80)
			)
			(3 (self changeState: 0))
			(4
				(AirplanePrint 40 32)
				(= askedForOrder 0)
				(= cycles 120)
			)
			(5
				(AirplanePrint 40 33 67 40 40)
				(self changeState: 0)
			)
			(6
				(AirplanePrint 40 34)
				(self changeState: 0)
			)
			(7 (= cycles 20) (= seconds 0))
			(8
				(stewardess setMotion: MoveTo 232 72 self)
			)
			(9
				(stewardess setMotion: MoveTo 255 77 self)
			)
			(10
				(stewardess loop: 2)
				(RedrawCast)
				(if (> alcoholicDrinksConsumed 2)
					(= egoDrunk 1)
				else
					(= egoDrunk 0)
				)
				(curRoom newRoom: 41)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'affirmative')
						(if (and (== state 4) (not askedForOrder))
							(AirplanePrint 40 32)
							(= askedForOrder 1)
						else
							(event claimed: 0)
						)
					)
					((Said 'n')
						(if (and (== state 4) (not askedForOrder))
							(AirplanePrint 40 35 67 30 30 33 smallFont)
							(= askedForOrder 1)
							(self changeState: 5)
						else
							(event claimed: 0)
						)
					)
					((Said 'stand,(get<up)')
						(if (!= state 4)
							(AirplanePrint 40 36)
						else
							(event claimed: 0)
						)
					)
					((Said 'call/attendant')
						(if (== state 4)
							(Print 40 37)
						else
							(Print 40 18 #at 40 30 #font smallFont)
							(= local7 1)
						)
					)
					((Said 'drink') (Print 40 38))
					((Said 'chat/attendant')
						(if (== state 4)
							(AirplanePrint 40 39)
						else
							(Print 40 40)
						)
					)
					((Said 'ask/number<phone')
						(if (!= state 4)
							(Print 40 41)
						else
							(AirplanePrint 40 42)
						)
					)
					((Said '/none')
						(if (== state 4)
							(AirplanePrint 40 43)
							(self changeState: 5)
						else
							(event claimed: 0)
						)
					)
					(
						(or
							(Said 'buy,ask,order,(get<!*)')
							(Said '/drink,chow,coffee,water,coca,beer,booze')
							(Said '//drink,chow,coffee,water,coca,beer,booze')
						)
						(event claimed: 0)
						(cond 
							((!= state 4) (Print 40 44) (event claimed: 1))
							(
								(or
									(Said '/coca,(drink<soft)')
									(Said '//coca,(drink<soft)')
								)
								(if (>= dollars 2)
									(= dollars (- dollars 2))
									(AirplanePrint 40 45)
									(AirplanePrint 40 46)
									(self changeState: 6)
								else
									(Print 40 47)
								)
							)
							((or (Said '/drink') (Said '//drink')) (AirplanePrint 40 32))
							((or (Said '/chow') (Said '//chow')) (AirplanePrint 40 48))
							((or (Said '/coffee') (Said '//coffee'))
								(AirplanePrint 40 49)
								(AirplanePrint 40 50)
								(self changeState: 6)
							)
							((or (Said '/water') (Said '//water'))
								(AirplanePrint 40 49)
								(AirplanePrint 40 51)
								(self changeState: 6)
							)
							(
							(or (Said '/beer,booze') (Said '//beer,booze'))
								(if (>= dollars 3)
									(++ alcoholicDrinksConsumed)
									(= dollars (- dollars 3))
									(AirplanePrint 40 52)
									(AirplanePrint 40 53)
									(AirplanePrint 40 54)
									(cond 
										((== alcoholicDrinksConsumed 3) (Print 40 55))
										((== alcoholicDrinksConsumed 2) (Print 40 56))
									)
									(self changeState: 6)
								else
									(Print 40 47)
								)
							)
							(else (Print 40 57) (event claimed: 1))
						)
					)
					(
					(Said 'use,go/bathroom,bathroom,(chamber<(bath,rest))') (Print 40 58))
					((Said 'pay/attendant') (Print 40 59))
				)
			)
		)
	)
)

(instance endStageTwo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (StageTwo state?) 4)
					(AirplanePrint 40 33 67 30 40)
				)
				(StageTwo changeState: 7)
			)
		)
	)
)
