;;; Sierra Script 1.0 - (do not remove this comment)
(script# 62)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm62 0
)

(local
	drinksBeingServed
	local1
	goingToSeat
	aStewardess
	aBore
	aCartLeft
	aCartRight
)
(instance rm62 of Room
	(properties
		picture 62
	)
	
	(method (init)
		(Load VIEW 611)
		(Load VIEW 610)
		(Load VIEW 604)
		(Load VIEW 223)
		(if (ego has: iPamphlet)
			(Load VIEW 26)
		)
		(Load SOUND 105)
		(theSound init:)
		(super init:)
		((= aBore (Prop new:))
			view: 602
			setCel: 0
			setPri: 5
			posn: 175 85
		)
		(if (== boreState 255)
			(aBore setLoop: 3 stopUpd: init:)
		else
			(aBore setLoop: 0 stopUpd: init:)
		)
		((= aCartLeft (Actor new:))
			view: 611
			setLoop: 0
			yStep: 8
			setCycle: Walk
			posn: 23 1103
			init:
			setScript: cartLeftScript
		)
		((= aCartRight (Actor new:))
			view: 610
			setLoop: 1
			yStep: 8
			setCycle: Walk
			posn: 308 1103
			init:
			setScript: cartRightScript
		)
		(self setRegions: AIRPLANE setScript: rm62Script)
		(if (== currentStatus egoBOARDPLANE)
			(= goingToSeat TRUE)
		else (NormalEgo)
		)
		(if goingToSeat
			(= goingToSeat FALSE)
			(HandsOff)
			(rm62Script changeState: 1)
			((= aStewardess (Actor new:))
				view: 600
				setLoop: 0
				posn: 11 101
				init:
				setCycle: Walk
			)
		)
		(if (== prevRoomNum 63)
			(ego posn: 298 101)
		else
			(ego posn: 37 101)
		)
		(ego init:)
	)
)

(instance rm62Script of Script
	(method (doit)
		(super doit:)
		(if (== currentStatus egoNORMAL)
			(if (& (ego onControl:) cBLUE)
				(curRoom newRoom: 61)
			)
			(if (& (ego onControl:) cGREEN)
				(curRoom newRoom: 63)
			)
		)
		(if (== state 18)
			(ShakeScreen 1 (Random 1 3))
		)
		(if
			(and
				(== gameState rgAIRPLANE)
				(not rgSeconds)
				(== currentStatus egoSITTING)
			)
			(= currentStatus egoSTOPPED)
			(= gameState 0)
			(rm62Script changeState: 21)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= seconds 3)
			)
			(2
				(Print 62 16)
				(ego setMotion: MoveTo 169 102 self)
			)
			(3
				(= seconds 3)
			)
			(4
				(Print 62 17)
				(= seconds 3)
			)
			(5
				(Print 62 18)
				(ego
					view: 163
					setLoop: 0
					illegalBits: 0
					setCel: 0
					cycleSpeed: 4
					posn: 166 97
					setCycle: EndLoop self
				)
			)
			(6
				(ego
					setLoop: 1
					cycleSpeed: 2
					setCycle: Forward
					moveSpeed: 10
					setStep: 1 1
					setPri: 6
					setMotion: MoveTo 167 94
				)
				(= cycles 40)
			)
			(7
				(ego
					setLoop: 2
					setCel: 0
					posn: 167 93
					cycleSpeed: 4
					setCycle: EndLoop self
				)
			)
			(8
				(ego stopUpd:)
				(Print 62 19)
				(= seconds 4)
			)
			(9
				(aStewardess posn: 11 101 setMotion: MoveTo 50 102 self)
			)
			(10
				(aStewardess setLoop: 3 setCycle: Forward)
				(= seconds 3)
			)
			(11
				(Print 62 20)
				(= seconds 3)
			)
			(12
				(Print 62 21)
				(Print 62 22)
				(= seconds 3)
			)
			(13
				(aStewardess setLoop: 2)
				(= seconds 2)
			)
			(14
				(Print 62 23)
				(aStewardess setLoop: 3)
				(= seconds 3)
			)
			(15
				(Print 62 24)
				(Print 62 25)
				(aStewardess
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 11 102 self
				)
			)
			(16
				(aStewardess dispose:)
				(= seconds 5)
			)
			(17
				(Print 62 26)
				(= seconds 5)
			)
			(18
				(= seconds 3)
			)
			(19
				(Print 62 27)
				(Print 62 28 #at -1 152)
				(Print 62 29)
				(Print 62 30)
				(= seconds 6)
			)
			(20
				(Print 62 31)
				(Print 62 32)
				(aBore setScript: boreScript)
				(ego setScript: sittingScript)
				(sittingScript changeState: 5)
			)
			(21
				(HandsOff)
				(Print 62 33)
				(= seconds 2)
			)
			(22
				(Print 62 34)
				(Print 62 35 #at -1 152)
				(Print 62 36)
				(Print 62 37)
				(= seconds 3)
			)
			(23
				(aCartLeft
					view: 223
					posn: 20 103
					setLoop: -1
					setCycle: Walk
					show:
					setMotion: MoveTo 140 104
				)
				(aCartRight
					view: 223
					posn: 308 103
					setLoop: -1
					setCycle: Walk
					show:
					setMotion: MoveTo 210 104 self
				)
				(theSound play:)
			)
			(24
				(Print 62 38)
				(Print 62 39)
				(Print 62 40)
				(Print 62 41)
				(= currentStatus egoCAPTURED)
				(curRoom newRoom: 96)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look,call,finger,call/bimbo,cart,bimbo')
			(if (== drinksBeingServed FALSE)
				(Print 62 0)
			else
				(Print 62 1)
			)
		)
		(if (Said 'jerk,jerk/cart')
			(if (== drinksBeingServed FALSE)
				(Print 62 2)
			else
				(Print 62 3)
			)
		)
		(if (Said 'look>')
			(if (Said '/bra,barstool,barstool')
				(if ((inventory at: iAirsickBag) ownedBy: curRoomNum)
					(Print 62 4)
				else
					(Print 62 5)
				)
			)
			(if (Said '/man')
				(if (== boreState 255)
					(Print 62 6)
				else
					(Print 62 7)
				)
			)
			(if (Said '[/airline,airport]')
				(if ((inventory at: iAirsickBag) ownedBy: curRoomNum)
					(Print 62 8)
				else
					(Print 62 9)
				)
			)
		)
		(if (Said 'bath')
			(cond 
				((== currentStatus egoSITTING)
					(YouAre)
				)
				((not (ego inRect: 158 94 194 106))
					(NotClose)
				)
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				(else
					(Ok)
					(ego setScript: sittingScript)
					(sittingScript changeState: 1)
				)
			)
		)
		(if (and (== currentStatus egoSITTING) (Said 'free,afix/belt'))
			(Ok)
		)
		(if
			(or
				(Said 'new,(get<up),(new<up)')
				(Said 'disembark[/barstool]')
			)
			(cond 
				((== currentStatus egoNORMAL)
					(YouAre)
				)
				((!= currentStatus egoSITTING)
					(NotNow)
				)
				(else
					(Ok)
					(ego setScript: sittingScript)
					(sittingScript changeState: 6)
				)
			)
		)
		(if (Said 'buy,get,buy/drink')
			(cond 
				((not drinksBeingServed)
					(Print 62 2)
				)
				((== currentStatus egoSITTING)
					(NotClose)
				)
				(else
					(Print 62 10)
				)
			)
		)
		(if (Said 'call/man,man')
			(Print 62 11)
		)
		(if (Said 'give,apply/pamphlet')
			(cond 
				((not (ego has: iPamphlet))
					(DontHave)
				)
				((!= currentStatus egoSITTING)
					(NotClose)
				)
				(else
					(ego put: iPamphlet -1)
					(theGame changeScore: 8)
					(boreScript changeState: 10)
					(Print 62 12 #icon 26 0 0)
					(Print 62 13)
				)
			)
		)
		(if (Said 'get/bag')
			(cond 
				((not ((inventory at: iAirsickBag) ownedBy: curRoomNum))
					(AlreadyTook)
				)
				((!= currentStatus egoSITTING)
					(NotClose)
				)
				(else
					(Print 62 14)
					(Print 62 15 #at -1 152)
					(ego get: iAirsickBag)
					(theGame changeScore: 5)
				)
			)
		)
	)
)

(instance sittingScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= seconds (= cycles 0))
				(HandsOff)
				(cartLeftScript changeState: 4)
				(cartRightScript changeState: 4)
				(ego setMotion: MoveTo 169 98 self)
			)
			(2
				(ego
					view: 163
					setLoop: 0
					setCel: 0
					cycleSpeed: 4
					posn: 166 97
					setCycle: EndLoop self
				)
			)
			(3
				(ego
					setLoop: 1
					cycleSpeed: 2
					setCycle: Forward
					setPri: 6
					moveSpeed: 10
					setStep: 1 1
					setMotion: MoveTo 166 94
					illegalBits: 0
				)
				(= seconds 3)
			)
			(4
				(ego
					setLoop: 2
					setCel: 0
					posn: 167 93
					cycleSpeed: 4
					setCycle: EndLoop self
				)
				(if (!= boreState 255)
					(aBore setScript: boreScript)
				)
			)
			(5
				(= seconds (= cycles 0))
				(ego setCel: setMotion: 0 stopUpd:)
				(= currentStatus egoSITTING)
				(User canControl: FALSE canInput: TRUE)
			)
			(6
				(HandsOff)
				(= seconds (= cycles 0))
				(ego
					cycleSpeed: 4
					setLoop: 2
					setCel: 255
					setCycle: BegLoop self
					setMotion: 0
				)
				(boreScript changeState: 9)
				(if (!= boreState 255)
					(cartLeftScript changeState: 1)
					(cartRightScript changeState: 1)
					(= drinksBeingServed TRUE)
					(ego observeControl: cYELLOW)
				)
			)
			(7
				(ego
					setLoop: 1
					cycleSpeed: 2
					setCycle: Forward
					posn: 166 93
					setPri: -1
					setMotion: MoveTo 166 98
				)
				(= seconds 3)
			)
			(8
				(ego
					setLoop: 0
					setCel: 255
					moveSpeed: 0
					setStep: 3 2
					setCycle: BegLoop self
					cycleSpeed: 4
				)
			)
			(9
				(ego posn: 169 97)
				(NormalEgo loopW)
			)
		)
	)
)

(instance cartLeftScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(aCartLeft posn: 20 103 setMotion: MoveTo 140 104 self)
			)
			(2
				(aCartLeft setMotion: MoveTo 30 104 self)
			)
			(3
				(if (== currentStatus egoSITTING)
					(self changeState: 4)
				else
					(= state 0)
					(= cycles (Random 5 30))
				)
			)
			(4
				(= seconds (= cycles 0))
				(aCartLeft setMotion: MoveTo 20 104 self)
			)
			(5
				(= drinksBeingServed FALSE)
				(aCartLeft posn: -1020 103)
			)
		)
	)
)

(instance cartRightScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(aCartRight
					posn: 308 103
					show:
					setMotion: MoveTo 210 104 self
				)
				(Print 62 42 #draw)
			)
			(2
				(aCartRight setMotion: MoveTo 308 104 self)
			)
			(3
				(if (== currentStatus egoSITTING)
					(self changeState: 4)
				else
					(= state 0)
					(= cycles (Random 5 30))
				)
			)
			(4
				(= seconds (= cycles 0))
				(aCartRight setMotion: MoveTo 308 104 self)
			)
			(5
				(= drinksBeingServed FALSE)
				(aCartRight posn: 1308 103)
			)
		)
	)
)

(instance boreScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aBore init: setLoop: 0 setCycle: EndLoop self)
			)
			(1
				(aBore setLoop: 1 setCycle: Forward)
				(= seconds (Random 2 4))
			)
			(2
				(switch (++ boreState)
					(1
						(Print 62 43)
						(Print 62 44)
						(Print 62 45)
					)
					(2
						(Print 62 46)
						(Print 62 47)
						(Print introductoryPhrase)
						(Print 62 48)
						(Print 62 49)
						(Print 62 50)
						(Print 62 51 #at -1 152)
					)
					(3
						(Print 62 52)
						(Print 62 53)
					)
					(4
						(Print 62 54)
						(Print 62 55)
						(Print 62 56)
						(Print 62 57)
						(Print 62 58)
						(Print 62 59)
						(Print 62 60)
					)
					(5
						(Print 62 61)
						(Print 62 62 #at -1 152)
					)
					(6
						(HandsOff)
						(Print 62 63)
						(Print 62 64)
						(Print 62 65)
						(self changeState: 5)
					)
				)
				(aBore setLoop: 2)
				(= seconds (Random 2 5))
			)
			(3
				(aBore setLoop: 0 setCel: 255 setCycle: BegLoop)
				(switch boreState
					(1 (= seconds (Random 40 60)))
					(2 (= seconds (Random 30 50)))
					(3 (= seconds (Random 20 40)))
					(4 (= seconds (Random 10 30)))
					(5 (= seconds (Random 0 20)))
				)
			)
			(4
				(aBore setLoop: 0 setCycle: EndLoop)
				(self changeState: 1)
			)
			(5
				(= seconds (= cycles 0))
				(= currentStatus egoSTOPPED)
				(ego hide:)
				(aBore
					view: 604
					setLoop: 0
					cycleSpeed: 2
					posn: 171 83
					cel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(aBore setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(7
				(= seconds 3)
			)
			(8
				(Print 62 66 #draw)
				(= currentStatus egoDYING)
			)
			(9
				(= seconds (= cycles 0))
				(if (!= boreState 255)
					(aBore setLoop: 0 setCel: 0 stopUpd:)
				)
			)
			(10
				(= seconds (= cycles 0))
				(= boreState 255)
				(SetRegionTimer rgAIRPLANE 3 33)
				(aBore setLoop: 3 setCel: 0 stopUpd:)
			)
		)
	)
)

(instance theSound of Sound
	(properties
		number 105
	)
)
