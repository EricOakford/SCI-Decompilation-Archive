;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
(include game.sh)
(use Main)
(use Door)
(use Intrface)
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
	leaveMsg
	roomState
	aDoor
	aRadar
	aHench1
	aHench2
)
(instance theSound of Sound
	(properties
		number 117
		loop -1
	)
)

(enum	;who's here
	nobodyHere
	copsHere
	krishnasHere
)

(instance rm50 of Room
	(properties
		picture 50
		horizon 1
	)
	
	(method (init)
		(Load VIEW 500)
		(Load VIEW 501)
		(Load VIEW 502)
		(super init:)
		((View new:)
			view: 500
			loop: 0
			cel: 0
			posn: 155 62
			setPri: 2
			addToPic:
		)
		((View new:)
			view: 500
			loop: 0
			cel: 1
			posn: 11 63
			setPri: 3
			addToPic:
		)
		((View new:)
			view: 500
			loop: 0
			cel: 2
			posn: 308 62
			setPri: 2
			addToPic:
		)
		((= aRadar (Prop new:))
			view: 500
			setLoop: 2
			setPri: 0
			posn: 285 2
			cycleSpeed: 1
			setCycle: Forward
			isExtra: TRUE
			init:
		)
		(= menInRoom nobodyHere)
		(if (== global111 1)
			(= currentEgoView 151)
		)
		(NormalEgo)
		(if (== prevRoomNum 52)
			(ego posn: 159 121)
		else
			(ego posn: 159 185)
		)
		(ego init:)
		((= aDoor (AutoDoor new:))
			view: 500
			setLoop: 1
			posn: 160 122
			setPri: 8
			entranceTo: 52
			doorCtrl: cBLUE
			doorBlock: cYELLOW
			roomCtrl: cGREEN
			msgLook: {Inside you see lines of businessmen waiting to buy tickets.}
			init:
		)
		(self setRegions: AIRPORT setScript: rm50Script)
		(if (== currentEgoView 151)
			(= currentStatus egoSTOPPED)
			(= roomState 0)
			(= menInRoom copsHere)
			((= aHench1 (Actor new:))
				view: 501
				loop: 2
				posn: 22 164
				illegalBits: cWHITE
				setStep: 4 3
				init:
				setAvoider: (Avoider new:)
			)
			((= aHench2 (Actor new:))
				view: 502
				loop: 2
				posn: 298 164
				illegalBits: cWHITE
				setStep: 4 3
				stopUpd:
				init:
				setAvoider: (Avoider new:)
			)
		else
			((View new:) view: 501 loop: 2 posn: 22 164 addToPic:)
			((View new:) view: 502 loop: 2 posn: 298 164 addToPic:)
			(if (!= gaveFlowerToKrishna TRUE)
				(= roomState 0)
				(= menInRoom krishnasHere)
				(Load VIEW 503)
				(Load VIEW 504)
				(Load VIEW 20)
				(Load SOUND 117)
				(theSound play:)
				((= aHench1 (Actor new:))
					view: 503
					setLoop: 2
					posn: 141 125
					illegalBits: cWHITE
					setCycle: Forward
					init:
					setAvoider: (Avoider new:)
				)
				((= aHench2 (Actor new:))
					view: 504
					setLoop: 2
					posn: 181 125
					illegalBits: cWHITE
					setCycle: Forward
					init:
					setAvoider: (Avoider new:)
				)
				(aBigFace
					view: 503
					setLoop: 6
					posn: 160 1054
					setPri: 15
					setCycle: Forward
					init:
				)
			)
		)
		(if menInRoom
			(aDoor locked: TRUE)
		)
	)
)

(instance rm50Script of Script
	(method (doit)
		(super doit:)
		(cond 
			((and (== roomState 0) (== menInRoom copsHere) (> 180 (ego y?)))
				(= roomState 1)
				(aHench1 setScript: (copScript new:))
				(aHench2 setScript: (copScript new:))
				(Print 50 0 #at -1 20)
			)
			((and (== roomState 0) (== menInRoom krishnasHere) (& (ego onControl:) cBLUE))
				(= roomState 1)
				(aHench1 setScript: (krishnaScript new:))
				(aHench2 setScript: (krishnaScript new:))
			)
			((& (ego onControl:) cCYAN)
				(if (== leaveMsg FALSE)
					(= leaveMsg TRUE)
					(Print 50 1)
				)
			)
			(else
				(= leaveMsg FALSE)
			)
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
				(= menInRoom nobodyHere)
				(= roomState 4)
				(= gaveFlowerToKrishna TRUE)
				(theSound stop:)
				(aHench1 setCel: 0)
				(aHench2 setCel: 0)
				(HandsOff)
				(Print 50 24 #at -1 20 #draw #icon 20 0 0)
				(ego put: iFlower -1)
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
				(aDoor locked: FALSE)
			)
			(4
				(cls)
				(Print 50 26 #at -1 152 #draw)
				(User canControl: TRUE canInput: TRUE)
			)
		)
	)
	
	(method (handleEvent event &tmp i)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look>')
				(cond 
					((Said '/man')
						(cond 
							((== menInRoom krishnasHere)
								(Print 50 2)
							)
							((== menInRoom copsHere)
								(Print 50 3)
							)
							(else
								(Print 50 4)
							)
						)
					)
					((Said '/cult,other')	;EO: fixed said spec
						(if (== menInRoom krishnasHere)
							(aBigFace posn: 160 54)
							(Timer setReal: aBigFace 5)
							(HandsOff)
						else
							(Print 50 5)
						)
					)
					((Said '/cop')
						(if (== menInRoom copsHere)
							(Print 50 3)
						else
							(Print 50 4)
						)
					)
					((Said '[/airport,building]')
						(Print 50 6)
						(Print 50 7)
						(if (!= gaveFlowerToKrishna TRUE)
							(Print 50 8)
						)
					)
				)
			)
			((Said 'call>')
				(cond 
					((Said '/man')
						(cond 
							((== menInRoom krishnasHere)
								(Print 50 2)
							)
							((== menInRoom copsHere)
								(Print 50 3)
							)
							(else
								(Print 50 4)
							)
						)
					)
					((Said '/cult,other')	;EO: fixed said spec
						(if (== menInRoom krishnasHere)
							(Print 50 9)
							(Print 50 10)
						else
							(Print 50 5)
						)
					)
					((Said '/cop')
						(cond 
							((== menInRoom copsHere)
								(Print 50 11)
							)
							(
								(or
									(ego inRect: 9 156 46 183)
									(ego inRect: 273 156 312 183)
								)
								(Print 50 12)
								(Print 50 13)
							)
							(else
								(NotClose)
							)
						)
					)
				)
			)
			((Said 'wear,alter,(get<off),drain,(conceal<on)/job,bra,bra,bikini')
				(Print 50 14)
			)
			((Said 'play/music,onklunk')
				(Print 50 15)
				(Print 50 16)
			)
			((Said 'give,throw,bribe,finger,conceal,buy,buy,conceal>')
				(= i (inventory saidMe:))
				(cond 
					((!= currentStatus egoNORMAL)
						(NotNow)
					)
					(
						(or
							(not i)
							(not (ego has: (inventory indexOf: i)))
						)
						(event claimed: FALSE)
					)
					(
						(and
							(!= gaveFlowerToKrishna TRUE)
							(< 25 (ego distanceTo: aHench1))
							(< 25 (ego distanceTo: aHench2))
						)
						(NotClose)
					)
					((== (inventory indexOf: i) iWadODough)
						(Print 50 17)
					)
					((!= (inventory indexOf: i) iFlower)
						(Print 50 18)
					)
					(else
						(Ok)
						(self changeState: 2)
					)
				)
			)
		)
	)
)

(instance copScript of Script
	(method (changeState newState &tmp theX theY)
		(switch (= state newState)
			(0
				(client
					setLoop: -1
					setMotion: Chase ego 11 self
					setCycle: Walk
				)
				(HandsOff)
			)
			(1
				(if (== roomState 1)
					(Print 50 27 #at -1 20)
					(= currentStatus egoSTOPPED)
					(= roomState 2)
					(= theY (- (ego y?) 1))
					(if (< (ego x?) (client x?))
						(= theX (+ (ego x?) 19))
					else
						(= theX (- (ego x?) 19))
					)
					(client ignoreActors: posn: theX theY)
					(ego
						setMotion: 0
						setLoop: (if (> theX (ego x?)) 0 else 1)
						ignoreActors:
						stopUpd:
					)
					(= seconds 3)
				)
			)
			(2
				(Print 50 28)
				(= currentStatus egoDYING)
			)
		)
	)
)

(instance krishnaScript of Script
	(method (changeState newState &tmp theX theY)
		(switch (= state newState)
			(0
				(client
					setLoop: -1
					setMotion: Chase ego 11 self
					setCycle: Walk
				)
				(HandsOff)
			)
			(1
				(if (== roomState 1)
					(= currentStatus egoSTOPPED)
					(= roomState 2)
					(HandsOff)
					(= theY (- (ego y?) 2))
					(if (< (ego x?) (client x?))
						(= theX (+ (ego x?) 9))
					else
						(= theX (- (ego x?) 9))
					)
					(client
						loop: (+ 3 (client loop?))
						cel: 0
						ignoreActors:
						posn: theX theY
						setCycle: EndLoop self
						setStep: 4 3
					)
					(ego
						setLoop: (if (> theX (ego x?)) 0 else 1)
						stopUpd:
					)
					(Print 50 29 #at -1 20 #draw)
				)
			)
			(2
				(Print 50 30 #draw)
				(Print 50 31 #at -1 20 #draw)
				(= currentStatus egoCAPTURED)
				(curRoom newRoom: 96)
			)
		)
	)
)

(instance aBigFace of Prop
	(method (cue)
		(Print 50 32 #draw)
		(Print 50 33 #at -1 152)
		(self posn: 160 1054)
		(HandsOn)
	)
)
