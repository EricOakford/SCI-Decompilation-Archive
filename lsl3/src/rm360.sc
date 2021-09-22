;;; Sierra Script 1.0 - (do not remove this comment)
(script# 360)
(include game.sh)
(use Main)
(use Door)
(use Intrface)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm360 0
)
(synonyms
	(man man man attendant robin)
)

(local
	talkCount
)
(instance rm360 of Room
	(properties
		picture 360
		horizon 111
		north 390
		east 365
		south 300
	)
	
	(method (init)
		(if (ego has: iSpaKeycard)
			(Load VIEW 715)
			(Load VIEW 9)
			(Load SOUND 11)
		)
		(super init:)
		(addToPics
			add: atpRightCardHole
			add: atpLeftCardHole
			add: atpRearCardHole
			add: atpFatCity
			add: atpBboard
			add: atpShelves
			add: atpSumtin
			add: atpBlender
			doit:
		)
		(aTanBoothDoor init:)
		(aStudioDoor init:)
		(aLockerDoor init:)
		(if (not playingAsPatti)
			(aRobin init:)
		)
		(self setLocales: ROBIN_LARRY setScript: RoomScript)
		(NormalEgo)
		(cond 
			((== prevRoomNum 390)
				(ego posn: 198 122 loop: 2)
				(aStudioDoor
					close:
					locked: TRUE
				)
				(if (== currentStatus egoAUTO)
					(HandsOff)
					(aBambi init:)
					(ego view: 720 posn: 191 122 illegalBits: 0 ignoreActors:)
					(music number: 399 loop: musicLoop play:)
					(RoomScript changeState: 11)
				)
			)
			((== prevRoomNum 370)
				(ego posn: 27 173)
				(aLockerDoor
					close:
					locked: TRUE
				)
			)
			((== prevRoomNum 365)
				(TheMenuBar draw: state: TRUE)
				(StatusLine enable:)
				(ego loop: 1 posn: 294 177)
			)
			(else
				(ego posn: 159 186 loop: 3)
			)
		)
		(ego init:)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (!= currentStatus egoAUTO)
			(ego
				observeControl: (& (ego onControl:) cLRED)
				ignoreControl: (& (ego onControl:) cMAGENTA)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 0)
			)
			(1
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 60 160 self
				)
			)
			(2
				(ego
					view: 715
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(NormalEgo)
				(HandsOff)
				(if (not (Btst fBeenInLockerRoom))
					(Bset fBeenInLockerRoom)
					(theGame changeScore: 3)
					(Print 360 24 #icon 9 0 0)
				)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 12 171 self
				)
				(aLockerDoor
					locked: FALSE
					force: TRUE
					open:
				)
			)
			(4
				(ego setMotion: MoveTo 0 171 self)
			)
			(5
				(aLockerDoor force: 1 close:)
				(soundFX number: 11 loop: 1 play:)
				(ego setMotion: MoveTo -20 171 self)
			)
			(6
				(curRoom newRoom: 370)
			)
			(7
				(HandsOff)
				(Ok)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 217 124 self
				)
			)
			(8
				(ego
					view: 715
					setCel: 0
					setLoop: 2
					setCycle: EndLoop self
				)
			)
			(9
				(if (not (Btst fBeenInTanningRoom))
					(Bset fBeenInTanningRoom)
					(theGame changeScore: 3)
					(Print 360 24 #icon 9 0 0)
				)
				(NormalEgo)
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 197 118 self
				)
				(aStudioDoor locked: 0 force: 1 open:)
			)
			(10
				(ego setMotion: MoveTo 197 0)
			)
			(11
				(= cycles 10)
			)
			(12
				(aBambi setMotion: MoveTo 225 165 self)
				(= cycles 5)
			)
			(13
				(ego setMotion: MoveTo 225 165 self)
			)
			(14
				(Print 360 25)
				(Print 360 26)
				(aBambi setMotion: MoveTo 294 176)
			)
			(15
				(ego setMotion: MoveTo 280 179 self)
			)
			(16
				(aBambi
					cycleSpeed: 3
					setLoop: 3
					cel: 0
					setCycle: EndLoop self
				)
			)
			(17
				(theGame changeScore: 3)
				(= cycles 10)
			)
			(18
				(aBambi setCycle: BegLoop)
				(aTanBoothDoor setCycle: EndLoop self)
			)
			(19
				(aTanBoothDoor stopUpd:)
				(= cycles 20)
			)
			(20
				(Print 360 27)
				(= cycles 10)
			)
			(21
				(aBambi
					cycleSpeed: 0
					setCycle: Walk
					setLoop: 0
					setMotion: MoveTo 316 175 self
				)
			)
			(22
				(ego setMotion: MoveTo 333 174)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			(
				(or
					(Said 'use,backdrop/keycard,card')
					(Said 'backdrop,backdrop/keycard,card/door,hole')
					(Said 'backdrop,backdrop//keycard,card')
					(Said 'unbolt,open/door')
				)
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (ego has: iSpaKeycard))
						(Print 360 0)
					)
					(
						(and
							(not (& (ego onControl:) (aLockerDoor doorCtrl?)))
							(not (& (ego onControl:) (aStudioDoor doorCtrl?)))
							(not (& (ego onControl:) cMAGENTA))
						)
						(NotClose)
					)
					((& (ego onControl:) cMAGENTA)
						(Print 360 1)
						(Print 360 2)
					)
					((& (ego onControl:) (aLockerDoor doorCtrl?))
						(RoomScript changeState: 1)
					)
					((& (ego onControl:) (aStudioDoor doorCtrl?))
						(RoomScript changeState: 7)
					)
				)
			)
			((Said '/club,class,bell,booth,aerobic')
				(Print 360 3)
			)
			((Said '/bookcase,buffet')
				(Print 360 4)
			)
			((Said '/blender')
				(Print 360 5)
			)
			((Said '/blouse')
				(Print 360 6)
			)
			((Said 'get/soap')
				(Print 360 7)
			)
			((Said 'look>')
				(cond 
					((Said '/door')
						(cond 
							((& (ego onControl:) (aLockerDoor doorCtrl?))
								(Print 360 8)
							)
							((& (ego onControl:) (aStudioDoor doorCtrl?))
								(Print 360 9)
							)
							((& (ego onControl:) cMAGENTA)
								(Print 360 10)
							)
							(else
								(Print 360 11)
							)
						)
					)
					((Said '/awning,board,note,art')
						(Print 360 12)
					)
					((Said '/man')
						(if (cast contains: aRobin)
							(Print 360 13)
						else
							(Print 360 14)
						)
					)
					((Said '/buffet,buffet<behind,back')
						(if (cast contains: aRobin)
							(Print 360 15)
						else
							(Print 360 16)
							(Print 360 17)
						)
					)
					((Said '/buffet,buffet')
						(if playingAsPatti
							(Print 360 18)
						else
							(Print 360 19)
						)
					)
					((Said '/burn')
						(Print 360 20)
					)
					((Said '/board,bulletin,note,awning')
						(Print 360 21)
					)
					((Said '/announcement')
						(Print 360 22)
					)
					((Said '[/club,club,area]')
						(Print 360 23)
					)
				)
			)
		)
	)
)

(instance aRobin of Actor
	(properties
		y 105
		x 133
		view 362
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			setPri: 10
			setCycle: Walk
			setScript: ManScript
		)
	)
)

(instance ManScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 4 8))
			)
			(1
				(aRobin
					illegalBits: 0
					cycleSpeed: 0
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo (Random 101 155) 105 self
				)
			)
			(2
				(= seconds (Random 1 3))
			)
			(4
				(aRobin setLoop: 3)
				(= seconds (Random 4 8))
				(= state -1)
			)
			(6
				(HandsOff)
				(= seconds 0)
				(aRobin setMotion: 0 setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(7
				(aRobin setCel: 0)
				(switch register
					(1
						(Print 360 36)
					)
					(2
						(Print 360 37)
					)
					(3
						(Print 360 38)
					)
					(4
						(cond 
							((< newspaperState NSpHere)
								(Print 360 39)
							)
							((== newspaperState NSpHere)
								(Print 360 40)
							)
							(else
								(Print 360 41)
							)
						)
					)
					(101
						(Print 360 42)
					)
					(102
						(Print 360 43)
					)
					(103
						(Print 360 44)
						(Print 360 45)
					)
					(104
						(Print 360 46)
					)
					(105
						(Print 360 47)
					)
					(106
						(Print 360 48)
					)
					(107
						(Print 360 49)
					)
					(108
						(Print 360 50)
					)
					(else 
						(Print 360 51)
						(= talkCount 4)
					)
				)
				(= register 0)
				(HandsOn)
				(self changeState: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'address/man')
				(cond 
					(playingAsPatti
						(Print 360 28)
					)
					((not (& (ego onControl:) cLGREY))
						(NotClose)
					)
					(else
						(ManScript changeState: 6 register: (++ talkCount))
					)
				)
			)
			((Said 'ask>')
				(cond 
					(playingAsPatti
						(Print 360 28)
					)
					((not (& (ego onControl:) cLGREY))
						(NotClose)
					)
					((or (Said '/door') (Said '//door'))
						(Print 360 29)
						(ManScript changeState: 6 register: 102)
					)
					(
						(or
							(Said '/keycard,camp,key,membership,card')
							(Said 'enroll')
							(Said '//keycard,key,camp,membership,card')
						)
						(Print 360 30)
						(ManScript changeState: 6 register: 104)
					)
					((or (Said '/bambi,tape') (Said '//tape,bambi'))
						(Print 360 31)
						(ManScript changeState: 6 register: 106)
					)
					((or (Said '/locker') (Said '//locker'))
						(Print 360 32)
						(ManScript changeState: 6 register: 108)
					)
					((or (Said '/combination') (Said '//combination'))
						(Print 360 33)
						(ManScript changeState: 6 register: 107)
					)
					(else
						(ManScript changeState: 6 register: 103)
					)
				)
				(event claimed: TRUE)
			)
			((Said 'show/keycard')
				(cond 
					((not (ego has: iSpaKeycard))
						(DontHave)
						(event claimed: TRUE)
					)
					((not (& (ego onControl:) cLGREY))
						(NotClose)
					)
					(else
						(Print 360 34 #icon 9 0 0)
						(ManScript changeState: 6 register: 101)
					)
				)
			)
			((Said 'give')
				(if (& (ego onControl:) cLGREY)
					(ManScript changeState: 6 register: 105)
				else
					(NotClose)
				)
			)
			((Said '/man')
				(Print 360 35)
			)
		)
	)
)

(instance aBambi of Actor
	(properties
		y 126
		x 200
		view 393
		loop 2
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			setCycle: Walk
		)
	)
)

(instance aTanBoothDoor of Prop
	(properties
		y 163
		x 296
		view 360
		loop 1
		cycleSpeed 2
	)
)

(instance aStudioDoor of Door
	(properties
		y 117
		x 199
		view 360
		loop 2
		entranceTo 390
		locked TRUE
		doorCtrl cCYAN
		roomCtrl cRED
		doorBlock cLMAGENTA
	)
)

(instance aLockerDoor of Door
	(properties
		y 172
		x 11
		view 360
		entranceTo 370
		locked TRUE
		roomCtrl 0
	)
)

(instance atpRightCardHole of PicView
	(properties
		y 165
		x 314
		view 360
		loop 3
		priority 14
	)
)

(instance atpLeftCardHole of PicView
	(properties
		y 143
		x 43
		view 360
		loop 3
		cel 1
		priority 11
	)
)

(instance atpRearCardHole of PicView
	(properties
		y 103
		x 224
		view 360
		loop 3
		cel 2
		priority 8
	)
)

(instance atpFatCity of PicView
	(properties
		y 40
		x 129
		view 360
		loop 4
		priority 0
	)
)

(instance atpBboard of PicView
	(properties
		y 134
		x 277
		view 360
		loop 4
		cel 1
		priority 9
	)
)

(instance atpShelves of PicView
	(properties
		y 104
		x 131
		view 360
		loop 4
		cel 2
		priority 6
	)
)

(instance atpSumtin of PicView
	(properties
		y 93
		x 170
		view 360
		loop 4
		cel 3
		priority 8
	)
)

(instance atpBlender of PicView
	(properties
		y 109
		x 91
		view 360
		loop 4
		cel 4
		priority 9
	)
)
