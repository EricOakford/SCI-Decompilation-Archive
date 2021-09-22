;;; Sierra Script 1.0 - (do not remove this comment)
(script# 470)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm470 0
)

(local
	floorRoomNum
	floorNumber
)
(instance rm470 of Room
	(properties
		picture 470
	)
	
	(method (init)
		(if (ego has: iPenthouseKey)
			(Load VIEW 12)
		)
		(Load VIEW (+ 715 (* 100 playingAsPatti)))
		(Load SOUND 460)
		(super init:)
		(aBeamFront init:)
		(aBeamRear init:)
		(aDoor init:)
		(aFloor init:)
		(addToPics add: atpPanel doit:)
		(self setScript: RoomScript)
		(NormalEgo 3)
		(ego posn: 161 137 init:)
		(if (> prevRoomNum curRoomNum)
			(= floorNumber 8)
		)
		(aLight setPri: 4 setCel: floorNumber init: stopUpd:)
		(music number: 32 loop: musicLoop play:)
	)
)

(instance RoomScript of Script
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= seconds 12)
			)
			(1
				(Print 470 18)
				(self changeState: 13)
			)
			(2
				(Ok)
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 179 130 self)
			)
			(3
				(ego
					cycleSpeed: 1
					view: (+ 715 (* 100 playingAsPatti))
					loop: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(if (not (Btst fEnteredElevator))
					(Bset fEnteredElevator)
					(theGame changeScore: 4)
					(Print 470 19 #icon 12 0 0)
				)
				(ego setCycle: BegLoop self)
			)
			(5
				(NormalEgo loopN)
				(= cycles 10)
				(if (== floorRoomNum 460)
					(= state 8)
				)
			)
			(6
				(aLight setCel: (++ floorNumber))
				(if (>= floorNumber 9)
					(self changeState: 12)
				else
					(aBeamRear setMotion: MoveTo 159 260)
					(aBeamFront setMotion: MoveTo 160 260 self)
				)
			)
			(7
				(aBeamRear posn: 159 -16 setMotion: MoveTo 159 130)
				(aBeamFront posn: 160 -32 setMotion: MoveTo 160 80 self)
			)
			(8
				(aBeamRear posn: 159 48 setMotion: MoveTo 159 128)
				(aBeamFront setMotion: MoveTo 160 160 self)
				(= state 5)
			)
			(9
				(aLight setCel: (-- floorNumber))
				(if (== floorNumber 0)
					(self changeState: 12)
				else
					(aBeamRear setMotion: MoveTo 159 80)
					(aBeamFront setMotion: MoveTo 160 80 self)
				)
			)
			(10
				(aBeamRear posn: 159 104 setMotion: MoveTo 159 -32)
				(aBeamFront setMotion: MoveTo 160 -32 self)
			)
			(11
				(aBeamRear posn: 159 232 setMotion: MoveTo 159 128)
				(aBeamFront posn: 160 260 setMotion: MoveTo 160 160 self)
				(= state 8)
			)
			(12
				(aFloor
					setLoop: (if (== floorRoomNum 460) 4 else 0)
					stopUpd:
				)
				(= seconds 3)
			)
			(13
				(HandsOff)
				(soundFX number: 460 loop: 1 play:)
				(aDoor setCycle: EndLoop self)
			)
			(14
				(aDoor stopUpd:)
				(ego illegalBits: 0 setMotion: MoveTo 160 128 self)
			)
			(15
				(ego setMotion: MoveTo 160 124 self)
			)
			(16
				(music fade:)
				(if (not floorRoomNum)
					(= floorRoomNum prevRoomNum)
				)
				(curRoom newRoom: floorRoomNum)
			)
		)
	)
	
	(method (handleEvent event &tmp [str 50])
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((or (Said 'use/key') (Said 'drag/button,carpet'))
				(Print 470 0)
			)
			(
				(or
					(Said '/penthouse,9,9')
					(Said '//penthouse,9,9')
					(Said 'go,drag/penthouse,9,9')
				)
				(cond 
					((not (ego has: iPenthouseKey))
						(Print 470 1)
						(Print (Format @str 470 2 currentEgo) #at -1 144)
					)
					(floorRoomNum
						(Print 470 3)
					)
					((> floorNumber 7)
						(Print 470 4)
					)
					(else
						(if playingAsPatti
							(= floorRoomNum 484)
						else
							(= floorRoomNum 480)
						)
						(self changeState: 2)
					)
				)
			)
			(
				(or
					(Said '/carpet,1,area')
					(Said '//carpet,1,(carpet<first),area')
					(Said 'go,drag/carpet,1,area')
				)
				(cond 
					((not (ego has: iPenthouseKey))
						(Print 470 1)
						(Print (Format @str 470 2 currentEgo) #at -1 144)
					)
					(floorRoomNum
						(Print 470 3)
					)
					((== floorNumber 0)
						(Print 470 4)
					)
					(else
						(= floorRoomNum 460)
						(self changeState: 2)
					)
				)
			)
			((Said 'up,down,open,close/door,carpet,elevator')
				(Print 470 5)
			)
			((Said 'drag,go/button,door,carpet')
				(Print 470 6)
			)
			((Said 'hear')
				(if playingAsPatti
					(Print 470 7)
				else
					(Print 470 8)
				)
			)
			((Said 'exit,go,exit,done,cease')
				(Ok)
				(Print 470 9)
			)
			((and (ego has: iSpaKeycard) (Said '/keycard'))
				(Print 470 10)
			)
			((Said 'pick,attack,break/bolt')
				(Print 470 11)
			)
			((Said 'look>')
				(cond 
					((Said '/burn')
						(Printf 470 12 (+ 1 (aLight cel?)))
					)
					((Said '/handle,button')
						(Print 470 13)
					)
					((Said '/door')
						(Printf 470 14 (if (aDoor cel?) {open} else {closed}))
					)
					((Said '/(hole<key),bolt')
						(Print 470 15)
					)
					((Said '[/area,elevator]')
						(Print 470 16)
						(Print 470 17 #at -1 144)
					)
				)
			)
		)
	)
)

(instance atpPanel of PicView
	(properties
		y 110
		x 184
		view 470
		loop 1
		priority 5
	)
)

(instance aLight of Prop
	(properties
		y 62
		x 160
		view 460
		loop 2
	)
)

(instance aBeamFront of Actor
	(properties
		y 156
		x 160
		view 470
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			setPri: 15
			setLoop: (if (> machineSpeed 16) 3 else 5)
			setStep: 0 8
		)
	)
)

(instance aBeamRear of Actor
	(properties
		y 125
		x 160
		view 470
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			setPri: 2
			setLoop: (if (> machineSpeed 39) 2 else 5)
			setStep: 0 8
		)
	)
)

(instance aDoor of Prop
	(properties
		y 125
		x 160
		view 460
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self setCel: 0 species 6 stopUpd: ignoreActors:)
	)
)

(instance aFloor of Prop
	(properties
		y 125
		x 160
		view 470
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self
			setPri: 5
			setLoop: (if (== prevRoomNum 460) 4 else 0) species
			stopUpd:
		)
	)
)
