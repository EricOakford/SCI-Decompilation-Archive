;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room64 0
)

(local
	oldSpeed
	doorIsOpen
)
(instance Room64 of Room
	(properties
		picture 64
	)
	
	(method (init)
		(= horizon 0)
		(super init:)
		(addToPics add: RDoor doit:)
		(LDoor setPri: 14 stopUpd: init:)
		(Fifi setPri: 10 stopUpd: setScript: Undressing init:)
		(lArm setPri: 11 init:)
	)
	
	(method (doit &tmp theX theY)
		(if (== (rArm loop?) 7)
			(switch (rArm cel?)
				(0 (= theX 30) (= theY 10))
				(1 (= theX 12) (= theY 7))
				(2 (= theX 1) (= theY 1))
			)
			(nightie
				posn: (- (rArm x?) theX) (+ (rArm y?) theY)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance Undressing of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= global192 64)
				(lArm cycleSpeed: 2 setCycle: EndLoop self)
			)
			(1
				(lArm cycleSpeed: 2 setCycle: BegLoop self)
			)
			(2
				(dress setPri: 12 setCycle: EndLoop self init:)
				(bra setPri: 12 stopUpd: init:)
				(panties setPri: 12 stopUpd: init:)
				(hose setLoop: 1 setPri: 12 stopUpd: init:)
				(rArm setPri: 12 stopUpd: init:)
				(lArm loop: 4 cel: 0 posn: 148 56 stopUpd:)
				(Fifi cel: 1 forceUpd:)
			)
			(3
				(= oldSpeed speed)
				(theGame setSpeed: 1)
				(dress
					setLoop: 3
					setCel: 0
					posn: 143 125
					yStep: 6
					setMotion: MoveTo 143 161 self
				)
			)
			(4
				(HandsOn)
				(dress setCel: -1 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(5
				(theGame setSpeed: oldSpeed)
				(dress stopUpd:)
				(HandsOff)
				(if doorIsOpen (= state 4))
				(= cycles 1)
			)
			(6
				(panties loop: 2 posn: 134 91 forceUpd:)
				(hose
					loop: 1
					posn: 144 109
					startUpd:
					setCycle: Walk
					setMotion: MoveTo 146 155 self
				)
			)
			(7
				(hose stopUpd:)
				(rArm setCel: 1 forceUpd:)
				(lArm setCel: 1 forceUpd:)
				(panties startUpd: setCycle: EndLoop self)
			)
			(8
				(rArm setCel: 0 forceUpd:)
				(lArm setCel: 0 forceUpd:)
				(panties
					setLoop: 3
					setCel: 0
					posn: 135 141
					setMotion: MoveTo 135 155 self
				)
			)
			(9
				(rArm setCel: 2 forceUpd:)
				(lArm setCel: 2 forceUpd:)
				(panties setCycle: EndLoop self)
			)
			(10
				(panties stopUpd:)
				(lArm setCel: 0 forceUpd:)
				(bra setCycle: EndLoop self)
			)
			(11
				(rArm setCel: 0 forceUpd:)
				(bra
					setLoop: 5
					setCel: 0
					posn: 131 71
					startUpd:
					setMotion: MoveTo 131 110 self
				)
			)
			(12 (bra setCycle: EndLoop self))
			(13
				(bra stopUpd:)
				(rArm loop: 6 cel: 0 startUpd: setCycle: EndLoop self)
			)
			(14
				(HandsOff)
				(if doorIsOpen
					(= state 5)
					(= cycles 1)
				else
					(nightie setPri: 12 init:)
					(= cycles 1)
				)
			)
			(15
				(rArm loop: 7 cel: 0 setCycle: EndLoop self)
				(nightie cel: (- (NumCels nightie) 1))
			)
			(16
				(rArm loop: 5)
				(rArm cel: (- (NumCels rArm) 1))
				(lArm startUpd: setCycle: EndLoop self)
			)
			(17
				(rArm setCycle: BegLoop)
				(lArm setCycle: BegLoop)
				(nightie posn: 149 56 setCycle: BegLoop)
				(= seconds 3)
			)
			(18
				(HandsOn)
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance DoorOpening of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(LDoor setCycle: EndLoop self)
			)
			(1
				(if (!= (dress cel?) (- (NumCels dress) 1))
					(= state 0)
				else
					(Print 64 0 #at 62 140)
				)
				(= cycles 1)
			)
			(2
				(LDoor setCycle: BegLoop self)
			)
			(3
				(= doorIsOpen 0)
				(client setScript: 0)
			)
		)
	)
)

(instance RDoor of PicView
	(properties
		y 131
		x 213
		view 164
		priority 14
		signal ignrAct
	)
)

(instance LDoor of Prop
	(properties
		y 131
		x 106
		view 164
		loop 1
		signal ignrAct
		cycleSpeed 1
	)
	
	(method (handleEvent event)
		(if
			(and
				(not doorIsOpen)
				(User canInput?)
				(or (MousedOn self event shiftDown) (Said 'open/door'))
			)
			(event claimed: TRUE)
			(= doorIsOpen TRUE)
			(LDoor setScript: DoorOpening)
		)
	)
)

(instance Fifi of Prop
	(properties
		y 158
		x 145
		view 473
		signal ignrAct
	)
)

(instance lArm of Prop
	(properties
		y 56
		x 138
		view 473
		loop 1
		signal ignrAct
	)
)

(instance rArm of Prop
	(properties
		y 56
		x 138
		view 473
		loop 5
		signal ignrAct
	)
)

(instance nightie of Prop
	(properties
		y 68
		x 92
		view 473
		loop 8
		signal ignrAct
	)
)

(instance dress of Actor
	(properties
		y 110
		x 158
		yStep 6
		view 473
		loop 2
		signal ignrAct
		cycleSpeed 2
	)
)

(instance bra of Actor
	(properties
		y 71
		x 131
		yStep 6
		view 463
		loop 4
		signal ignrAct
	)
)

(instance panties of Actor
	(properties
		y 109
		x 146
		yStep 6
		view 463
		signal ignrAct
	)
)

(instance hose of Actor
	(properties
		y 109
		x 144
		yStep 6
		view 463
		signal ignrAct
	)
)
