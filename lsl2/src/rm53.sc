;;; Sierra Script 1.0 - (do not remove this comment)
(script# 53)
(include game.sh)
(use Main)
(use Door)
(use AirplaneActor)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm53 0
)

(local
	local0
	airplane
	gate
	conveyor4
	conveyor3
	conveyor2
	conveyor1
	traveler2
	agent
	traveler1
)
(instance theSound of Sound
	(properties
		number 5
		loop -1
	)
)

(instance rm53 of Room
	(properties
		picture 53
		horizon 5
		east 54
		west 52
	)
	
	(method (init)
		(Load VIEW 513)
		(Load VIEW 514)
		(Load VIEW 511)
		(super init:)
		((View new:)
			view: 513
			loop: 1
			cel: 0
			posn: 25 157
			setPri: 12
			addToPic:
		)
		((View new:)
			view: 513
			loop: 1
			cel: 1
			posn: 73 57
			setPri: 10
			addToPic:
		)
		((View new:)
			view: 513
			loop: 6
			cel: 0
			posn: 301 117
			setPri: 11
			addToPic:
		)
		((= conveyor1 (Prop new:))
			view: 513
			setLoop: 5
			setPri: 3
			posn: 198 59
			cycleSpeed: 1
			setCycle: Forward
			isExtra: TRUE
			init:
		)
		((= conveyor2 (Prop new:))
			view: 513
			setLoop: 4
			setPri: 4
			posn: 206 69
			cycleSpeed: 1
			setCycle: Forward
			isExtra: TRUE
			init:
		)
		((= conveyor3 (Prop new:))
			view: 513
			setLoop: 3
			setPri: 4
			posn: 218 96
			cycleSpeed: 1
			setCycle: Forward
			isExtra: TRUE
			init:
		)
		((= conveyor4 (Prop new:))
			view: 513
			setLoop: 2
			setPri: 10
			posn: 239 131
			cycleSpeed: 1
			setCycle: Forward
			isExtra: 1
			init:
		)
		((= agent (Prop new:))
			view: 514
			setLoop: 0
			setCel: 0
			setPri: 10
			posn: 187 123
			stopUpd:
			init:
		)
		((= traveler2 (Prop new:))
			view: 514
			setLoop: 1
			setCel: 0
			setPri: 1
			posn: 184 46
			stopUpd:
			init:
		)
		((= traveler1 (Actor new:))
			view: 514
			setLoop: 3
			setPri: 2
			posn: 128 36
			setStep: 1 1
			illegalBits: 0
			ignoreActors:
			init:
			hide:
		)
		((= airplane (Airplane new:))
			view: 511
			setCel: 0
			startX: 208
			startY: 23
			endX: 1
			endY: 23
			init:
		)
		(cond 
			((== prevRoomNum 54) (ego posn: 316 153))
			((== prevRoomNum 52) (ego posn: 51 119))
			(else (ego posn: 51 119))
		)
		(self setRegions: 500 setScript: rm53Script)
		(if (!= suitcaseBombState bombHOLDING)
			(NormalEgo)
		else
			(Load SOUND 5)
			(theSound play:)
			(HandsOff)
			(= currentStatus egoSuitcaseBombed)
			(rm53Script changeState: 9)
		)
		(ego init:)
		((= gate (AutoDoor new:))
			view: 513
			setLoop: 0
			posn: 293 156
			setPri: 11
			roomCtrl: 0
			locked: (if (== prevRoomNum 54) 0 else 1)
			msgLook:
				{There is a barely perceptible, blue gate in the east wall under that painting.}
			msgLookLock:
				{The gate is controlled by the Customs Official standing behind the counter.}
			msgLocked:
				{The gate is locked. Try talking to the friendly looking gentleman behind the counter.}
			msgExcept: {...except it's locked!}
			msgFunny: {Most people never knock on a gate!}
			msgCloser:
				{When the man releases the gate, it opens just by walking near it.}
			init:
		)
	)
)

(instance rm53Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 1) $0004) (curRoom newRoom: 52))
	)
	
	(method (changeState newState &tmp inventoryFirst temp1)
		(switch (= state newState)
			(1
				(HandsOff)
				(ego setLoop: 3)
				(Print 53 18)
				(= seconds 3)
			)
			(2
				(agent setCycle: Forward)
				(= seconds 3)
			)
			(3
				(agent setCel: 0)
				(Print 53 19 #draw)
				(= seconds 3)
			)
			(4
				(agent setCycle: Forward)
				(= seconds 3)
			)
			(5
				(agent setCel: 0)
				(Print 53 20 #draw)
				(= seconds 3)
			)
			(6
				(Print 53 21)
				(= inventoryFirst (inventory first:))
				(while inventoryFirst
					(if
					((= temp1 (NodeValue inventoryFirst)) ownedBy: ego)
						(temp1 showSelf:)
					)
					(= inventoryFirst (inventory next: inventoryFirst))
				)
				(Print 53 22)
				(= seconds 3)
			)
			(7
				(agent setCycle: Forward)
				(= seconds 3)
			)
			(8
				(User canControl: 1 canInput: 1)
				(agent setCel: 0 stopUpd:)
				(ego setLoop: -1)
				(if (ego has: 17) (Print 53 23) (Print 53 24))
				(Print 53 25 #draw)
				(Print 53 26)
				(Print (Format @str 53 27 tritePhrase))
				(gate locked: 0)
			)
			(9
				(ego
					setStep: 4 3
					setLoop: 1
					setMotion: MoveTo 222 154 self
				)
			)
			(10
				(Print 53 28)
				(ego setMotion: MoveTo 157 150 self)
			)
			(11
				(Print (Format @str 53 29 tritePhrase))
				(ego setMotion: MoveTo 43 127 self)
			)
		)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'give,look,throw,conceal,conceal>')
			(cond 
				(
					(or
						(not (= inventorySaidMe (inventory saidMe:)))
						(not (ego has: (inventory indexOf: inventorySaidMe)))
					)
					(event claimed: 0)
				)
				((not (ego inRect: 171 143 198 149)) (PrintNotCloseEnough))
				((== (inventory indexOf: inventorySaidMe) 17) (Print 53 0))
				((!= (inventory indexOf: inventorySaidMe) 7) (Print 53 1))
				((== currentEgoView 149) (Print 53 2) (Print 53 3))
				((not global137)
					(= global137 1)
					(theGame changeScore: 5)
					(self changeState: 1)
				)
				(else
					(if (ego has: 17)
						(Print 53 4)
						(Print 53 5)
						(Print (Format @str 53 6 tritePhrase))
					else
						(Print (Format @str 53 7 tritePhrase))
					)
					(gate locked: 0)
				)
			)
		)
		(if (Said 'look>')
			(if (Said '/man,agent') (Print 53 8))
			(if (Said '/art') (Print 53 9))
			(if (Said '/belt,open,hole') (Print 53 10))
			(if (Said '[/airport,building,belt]')
				(Print 53 11)
				(Print 53 12)
			)
		)
		(if
			(or
				(Said '(crawl<in),board/open,hole,belt')
				(Said 'apply,(climb<on),board/belt')
			)
			(Print 53 13)
		)
		(if (Said '/bathing') (Print 53 14))
		(if (Said 'open/door,door') (Print 53 15))
		(if (Said 'call/man,agent')
			(Print (Format @str 53 16 introductoryPhrase))
			(Print 53 17)
		)
	)
)

(instance travelerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(traveler1
					posn: 128 36
					show:
					setCycle: Walk
					setMotion: MoveTo 182 37 self
				)
			)
			(2
				(traveler1
					setLoop: (+ (traveler1 loop?) 1)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(traveler2 setCycle: EndLoop self)
			)
			(4
				(traveler2 setLoop: 2 setCycle: Forward)
				(= seconds 10)
			)
			(5
				(traveler2 setLoop: 1 setCel: 255 setCycle: BegLoop self)
			)
			(6
				(traveler2 stopUpd:)
				(traveler1 setCycle: BegLoop self)
			)
			(7
				(traveler1
					setLoop: (- (traveler1 loop?) 1)
					setCycle: Walk
					setMotion: MoveTo 203 37 self
				)
			)
			(8
				(traveler1 setLoop: (if (== (traveler1 loop?) 3) 5 else 3))
				(self changeState: 0)
			)
		)
	)
)
