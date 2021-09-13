;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
(include sci.sh)
(use Main)
(use Door)
(use Intrface)
(use Chase)
(use Timer)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm50 0
)

(local
	menInRoom
	triedToLeave
	roomState
)
(instance theSound of Sound
	(properties
		number 117
		loop -1
	)
)

(instance rm50 of Rm
	(properties
		picture 50
		horizon 1
	)
	
	(method (init)
		(Load rsVIEW 500)
		(Load rsVIEW 501)
		(Load rsVIEW 502)
		(Load rsSCRIPT 985)
		(super init:)
		(addToPics add: aSign aEatSign aShitSign doit:)
		(aRadar
			setPri: 0
			cycleSpeed: 1
			setCycle: Fwd
			isExtra: 1
			init:
		)
		(= menInRoom 0)
		(if (== global111 1) (= currentEgoView 151))
		(NormalEgo)
		(if (== prevRoomNum 52)
			(ego posn: 159 121)
		else
			(ego posn: 159 185)
		)
		(ego init:)
		(aDoor
			setPri: 8
			entranceTo: 52
			doorCtrl: 2
			doorBlock: 16384
			roomCtrl: 4
			msgLook: {Inside you see lines of businessmen waiting to buy tickets.}
			init:
		)
		(self setRegions: 500 setScript: rm50Script)
		(if (== currentEgoView 151)
			(= currentStatus 1000)
			(= roomState 0)
			(= menInRoom 1)
			(aHench1
				view: 501
				loop: 2
				posn: 22 164
				illegalBits: -32768
				setStep: 4 3
				init:
			)
			(aHench2
				view: 502
				loop: 2
				posn: 298 164
				illegalBits: -32768
				setStep: 4 3
				stopUpd:
				init:
			)
		else
			(addToPics add: aCop1 aCop2 doit:)
			(if (!= gaveFlowerToKrishna 1)
				(= roomState 0)
				(= menInRoom 2)
				(Load rsVIEW 503)
				(Load rsVIEW 504)
				(Load rsVIEW 20)
				(Load rsSOUND 117)
				(theSound play:)
				(aHench1
					view: 503
					setLoop: 2
					posn: 141 125
					illegalBits: -32768
					setCycle: Fwd
					init:
				)
				(aHench2
					view: 504
					setLoop: 2
					posn: 181 125
					illegalBits: -32768
					setCycle: Fwd
					init:
				)
				(aBigFace
					view: 503
					setLoop: 6
					posn: 160 1054
					setPri: 15
					setCycle: Fwd
					init:
				)
			)
		)
		(if menInRoom (aDoor locked: 1))
	)
	
	(method (dispose)
		(DisposeScript 972)
		(DisposeScript 985)
		(super dispose:)
	)
)

(instance rm50Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(== roomState 0)
					(== menInRoom 1)
					(> 180 (ego y?))
				)
				(Print 50 0 #at -1 20)
				(= roomState 1)
				(aHench1 setScript: (copScript new:))
				(aHench2 setScript: (copScript new:))
			)
			(
				(and
					(== roomState 0)
					(== menInRoom 2)
					(& (ego onControl:) $0002)
				)
				(= roomState 1)
				(aHench1 setScript: (krishnaScript new:))
				(aHench2 setScript: (krishnaScript new:))
			)
			((& (ego onControl:) $0008)
				(if (== triedToLeave 0)
					(= triedToLeave 1)
					(Print 50 1)
				)
			)
			(else (= triedToLeave 0))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (++ airportEntranceMessage) 1)
					(= cycles 5)
				else
					(= seconds (Random 3 12))
				)
			)
			(1
				(switch airportEntranceMessage
					(1 (Print 50 19))
					(2 (Print 50 20))
					(3 (Print 50 21))
					(4 (Print 50 22))
					(else  (Print 50 23))
				)
			)
			(2
				(= menInRoom 0)
				(= roomState 4)
				(= gaveFlowerToKrishna 1)
				(theSound stop:)
				(aHench1 setCel: 0)
				(aHench2 setCel: 0)
				(HandsOff)
				(Print 50 24 #at -1 20 #draw #icon 20 0 0)
				(ego put: 20 -1)
				(= seconds 3)
			)
			(3
				(aHench1
					illegalBits: 0
					setCycle: Walk
					setLoop: 5
					setMotion: MoveTo 333 (aHench1 y?)
				)
				(aHench2
					illegalBits: 0
					setCycle: Walk
					setLoop: 5
					setMotion: MoveTo 333 (aHench2 y?) self
				)
				(Print 50 25 #icon 504 2 0 #at -1 20 #draw #dispose)
				(theGame changeScore: 7)
				(aDoor locked: 0)
			)
			(4
				(cls)
				(Print 50 26 #at -1 130 #draw)
				(aHench1 dispose:)
				(aHench2 dispose:)
				(User canControl: 1 canInput: 1)
			)
		)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look>')
				(cond 
					((Said '/man')
						(cond 
							((== menInRoom 2) (Print 50 2))
							((== menInRoom 1) (Print 50 3))
							(else (Print 50 4))
						)
					)
					((Said '/cult,,')
						(if (== menInRoom 2)
							(aBigFace posn: 160 54)
							(Timer setReal: aBigFace 5)
							(HandsOff)
						else
							(Print 50 5)
						)
					)
					((Said '/cop') (if (== menInRoom 1) (Print 50 3) else (Print 50 4)))
					((Said '[/airport,building]')
						(Print 50 6)
						(Print 50 7)
						(if (!= gaveFlowerToKrishna 1) (Print 50 8))
					)
				)
			)
			((Said 'call>')
				(cond 
					((Said '/man')
						(cond 
							((== menInRoom 2) (Print 50 2))
							((== menInRoom 1) (Print 50 3))
							(else (Print 50 4))
						)
					)
					((Said '/cult,,')
						(if (== menInRoom 2)
							(Print 50 9)
							(Print 50 10)
						else
							(Print 50 5)
						)
					)
					((Said '/cop')
						(cond 
							((== menInRoom 1) (Print 50 11))
							(
								(or
									(ego inRect: 9 156 46 183)
									(ego inRect: 273 156 312 183)
								)
								(Print 50 12)
								(Print 50 13)
							)
							(else (NotClose))
						)
					)
				)
			)
			(
				(Said
					'wear,alter,(get<off),drain,(conceal<on)/job,bra,bra,bikini'
				)
				(Print 50 14)
			)
			((Said 'play/music,onklunk') (Print 50 15) (Print 50 16))
			(
			(Said 'give,throw,bribe,finger,conceal,buy,buy,conceal>')
				(= inventorySaidMe (inventory saidMe:))
				(cond 
					((!= currentStatus 0) (NotNow))
					(
						(or
							(not inventorySaidMe)
							(not (ego has: (inventory indexOf: inventorySaidMe)))
						)
						(event claimed: 0)
					)
					(
						(and
							(!= gaveFlowerToKrishna 1)
							(< 25 (ego distanceTo: aHench1))
							(< 25 (ego distanceTo: aHench2))
						)
						(NotClose)
					)
					((== (inventory indexOf: inventorySaidMe) 6) (Print 50 17))
					((!= (inventory indexOf: inventorySaidMe) 20) (Print 50 18))
					(else (Ok) (self changeState: 2))
				)
			)
		)
	)
)

(instance copScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(client
					setLoop: -1
					setAvoider: (Avoid new:)
					setMotion: Chase ego 11 self
					setCycle: Walk
				)
				(HandsOff)
			)
			(1
				(if (== roomState 1)
					(Print 50 27 #at -1 20)
					(= currentStatus 1000)
					(= roomState 2)
					(= temp1 (- (ego y?) 1))
					(if (< (ego x?) (client x?))
						(= temp0 (+ (ego x?) 19))
					else
						(= temp0 (- (ego x?) 19))
					)
					(client ignoreActors: posn: temp0 temp1)
					(ego
						setMotion: 0
						setLoop: (if (> temp0 (ego x?)) 0 else 1)
						ignoreActors:
						stopUpd:
					)
					(= seconds 3)
				)
			)
			(2
				(Print 50 28)
				(= currentStatus 1001)
			)
		)
	)
)

(instance krishnaScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(client
					setAvoider: (Avoid new:)
					setLoop: -1
					setMotion: Chase ego 11 self
					setCycle: Walk
				)
				(HandsOff)
			)
			(1
				(if (== roomState 1)
					(= currentStatus 1000)
					(= roomState 2)
					(HandsOff)
					(= temp1 (- (ego y?) 2))
					(if (< (ego x?) (client x?))
						(= temp0 (+ (ego x?) 9))
					else
						(= temp0 (- (ego x?) 9))
					)
					(client
						loop: (+ 3 (client loop?))
						cel: 0
						ignoreActors:
						posn: temp0 temp1
						setCycle: End self
						setStep: 4 3
					)
					(ego
						setLoop: (if (> temp0 (ego x?)) 0 else 1)
						stopUpd:
					)
					(Print 50 29 #at -1 20 #draw)
				)
			)
			(2
				(Print 50 30 #draw)
				(Print 50 31 #at -1 20 #draw)
				(= currentStatus 23)
				(curRoom newRoom: 96)
			)
		)
	)
)

(instance aBigFace of Prop
	(properties)
	
	(method (cue)
		(Print 50 32 #draw)
		(Print 50 33 #at -1 130)
		(self posn: 160 1054)
		(HandsOn)
	)
)

(instance aHench1 of Act
	(properties)
)

(instance aHench2 of Act
	(properties)
)

(instance aSign of PV
	(properties
		y 62
		x 155
		view 500
		priority 2
	)
)

(instance aEatSign of PV
	(properties
		y 63
		x 11
		view 500
		cel 1
		priority 3
	)
)

(instance aShitSign of PV
	(properties
		y 62
		x 308
		view 500
		cel 2
		priority 2
	)
)

(instance aCop1 of PV
	(properties
		y 164
		x 22
		view 501
		loop 2
	)
)

(instance aCop2 of PV
	(properties
		y 164
		x 298
		view 502
		loop 2
	)
)

(instance aRadar of Prop
	(properties
		y 2
		x 285
		view 500
		loop 2
	)
)

(instance aDoor of AutoDoor
	(properties
		y 122
		x 160
		view 500
		loop 1
		msgLook 0
		msgLookLock 18
		msgLocked 38
		msgExcept 59
		msgFunny 82
		msgCloser 102
	)
)
