;;; Sierra Script 1.0 - (do not remove this comment)
(script# 35)
(include game.sh)
(use Main)
(use Intrface)
(use Extra)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm35 0
)

(local
	local0
	spinachDipInRoom
	henchwomanAppeared
	henchwomanBeckons
)
(instance rm35 of Room
	(properties
		picture 35
		horizon 5
		south 31
	)
	
	(method (init)
		(Load VIEW 315)
		(Load VIEW 319)
		(super init:)
		(self setRegions: SHIP setScript: rm35Script)
		(if ((inventory at: iSpinachDip) ownedBy: curRoomNum)
			(= spinachDipInRoom TRUE)
			(aSpinachDip
				stopUpd:
				init:
			)
		else
			(self setRegions: HENCHWOMAN)
			(= henchView 316)
			(Load VIEW henchView)
			(aHench
				view: henchView
				illegalBits: cWHITE
				setCycle: Walk
				init:
				setScript: henchScript
			)
		)
		(addToPics
			add:
				aGirl1
				aGirl2
				aMan1
				aMan2
				aBoat
				aGirl3
				aMan3
				aStool
				aBar
				aBarGirl1
				aBarGirl2
			doit:
		)
		(aBartender
			stopUpd:
			setPri: 3
			init:
		)
		(aTV
			setPri: 3
			setCycle: Forward
			isExtra: TRUE
			init:
		)
		(aGirl1drinking
			setPri: 7
			minPause: 20
			maxPause: 40
			minCycles: 5
			maxCycles: 10
			init:
		)
		(aTitFeeler
			setPri: 6
			minPause: 20
			maxPause: 30
			minCycles: 5
			maxCycles: 8
			init:
		)
		(aGirl2drinking
			setPri: 7
			minPause: 22
			maxPause: 50
			minCycles: 7
			maxCycles: 12
			init:
		)
		(aGirl3drinking
			setPri: 11
			init:
		)
		(aManDrinking
			setPri: 11
			minPause: 32
			maxPause: 70
			minCycles: 5
			maxCycles: 10
			init:
		)
		(aShip
			setLoop: 6
			setPri: 0
			illegalBits: 0
			setStep: 1 1
			moveSpeed: 1
			init:
			setScript: shipScript
		)
		(NormalEgo)
		(ego posn: 157 157 init:)
	)
)

(instance rm35Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cBLUE)
			(if (== henchwomanAppeared FALSE)
				(curRoom newRoom: 31)
			else
				(Print 35 0)
				(Print 35 1)
				(= currentStatus egoCAPTURED)
				(curRoom newRoom: 95)
			)
		)
		(if (and henchwomanIsHere henchwomanBeckons (> (ego y?) 146))
			(= henchwomanBeckons FALSE)
			(= currentStatus egoCAPTURED)
			(= henchwomanAppeared TRUE)
			(curRoom south: 95)
			(Print 35 2)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= currentStatus egoAUTO)
				(HandsOff)
				(Ok)
				(ego setMotion: MoveTo 166 107 self)
			)
			(2
				(ego
					view: 104
					ignoreActors:
					illegalBits: 0
					setMotion: 0
					posn: 163 95
					setLoop: 0
					setPri: 6
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(= currentStatus egoSITTING)
				(if (and (== spinachDipInRoom FALSE) (not (henchScript state?)))
					(henchScript changeState: 1)
				)
				(User canInput: TRUE)
			)
			(4
				(Ok)
				(= currentStatus egoAUTO)
				(HandsOff)
				(ego
					setLoop: 0
					setMotion: 0
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(5
				(ego posn: 164 106)
				(NormalEgo loopN)
			)
			(6
				(= currentStatus egoSTOPPED)
				(HandsOff)
				(henchScript changeState: 255)
				(Print 35 33)
				(= seconds 3)
			)
			(7
				(Print 35 34)
				(Print 35 35)
				(Print 35 36)
				(aBartender setLoop: 0 setCycle: Forward)
				(= seconds 5)
			)
			(8
				(aBartender
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(9
				(= seconds 2)
			)
			(10
				(aBartender
					setLoop: 2
					cel: 0
					cycleSpeed: 0
					setCycle: EndLoop self
				)
				(ego setLoop: 1 cycleSpeed: 0 cel: 0 setCycle: EndLoop)
			)
			(11
				(aBartender stopUpd:)
				(Print 35 37)
				(ego setLoop: 2 cel: 0 setCycle: EndLoop)
				(= seconds 3)
			)
			(12
				(ego cycleSpeed: 1 setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(13
				(ego cycleSpeed: 0 setLoop: 4 cel: 0 setCycle: Forward)
				(= seconds 5)
			)
			(14
				(Print 35 38)
				(= seconds 3)
			)
			(15
				(Print 35 39)
				(= currentStatus egoCAPTURED)
				(curRoom newRoom: 96)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if
			(and
				henchwomanIsHere
				(< (henchScript state?) 5)
				(Said '/bimbo>')
			)
			(cond 
				((Said 'call/')
					(Print (Format @str 35 3 introductoryPhrase))
				)
				((Said 'look/')
					(Print 35 4)
				)
				(else
					(Print 35 5)
					(Print 35 6 #at -1 130)
				)
			)
		)
		(if (Said 'look>')
			(if (and henchwomanIsHere (Said '/bimbo'))
				(if (< (henchScript state?) 3)
					(Print 35 7)
				else
					(Print 35 8)
				)
			)
			(if (Said '/agent')
				(Print 35 9)
			)
			(if (Said '/buffet,man,bimbo,children')
				(Print 35 10)
				(Print 35 11 #at -1 130)
			)
			(if (Said '/cup,craft')
				(Print 35 12)
				(Print 35 13)
			)
			(if (Said '/burn')
				(Print 35 14)
			)
			(if (Said '/computer,krod')
				(Print 35 15)
			)
			(if (Said '/bottle,bar')
				(if (== currentStatus egoSITTING)
					(Print 35 16)
				else
					(Print 35 17)
				)
				(if spinachDipInRoom
					(Print 35 18)
				)
			)
			(if (and spinachDipInRoom (Said '/bowl,bread'))
				(Print 35 19)
			)
			(if (Said '[/airport]')
				(Print 35 20)
				(if spinachDipInRoom
					(Print 35 18)
				)
			)
		)
		(if (Said 'bath[/down,barstool]')
			(cond 
				((== currentStatus egoSITTING)
					(YouAre)
				)
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				(
					(and
						(not (ego inRect: 148 103 222 109))
						(not (ego inRect: 64 107 285 128))
					)
					(Print 35 21)
				)
				(else
					(self changeState: 1)
				)
			)
		)
		(if
			(or
				(Said 'new,(get<up),new[/up]')
				(Said 'disembark[/barstool]')
			)
			(cond 
				((== currentStatus egoNORMAL)
					(Print 35 22)
				)
				((!= currentStatus egoSITTING)
					(NotNow)
				)
				(else
					(self changeState: 4)
				)
			)
		)
		(if (Said 'call/agent')
			(cond 
				((== currentStatus egoNORMAL)
					(Print 35 22)
				)
				((!= currentStatus egoSITTING)
					(NotNow)
				)
				(else
					(Print (Format @str 35 23 introductoryPhrase))
					(Print 35 24)
					(Print 35 25 #at -1 130)
				)
			)
		)
		(if (Said 'call/bimbo')
			(cond 
				((== currentStatus egoNORMAL)
					(Print 35 22)
				)
				((!= currentStatus egoSITTING)
					(NotNow)
				)
				(else
					(Print (Format @str 35 26 introductoryPhrase))
					(Print 35 27)
					(if (> filthLevel 10)
						(Print 35 28)
					)
				)
			)
		)
		(if (Said 'call')
			(Print 35 29)
		)
		(if (or (Said 'give/i/beer') (Said 'buy/beer'))
			(cond 
				((== currentStatus egoNORMAL)
					(Print 35 22)
				)
				((!= currentStatus egoSITTING)
					(NotNow)
				)
				(else
					(Print 35 30)
				)
			)
		)
		(if (or (Said 'give/i/drink') (Said 'buy/drink'))
			(cond 
				((== currentStatus egoNORMAL)
					(Print 35 22)
				)
				((!= currentStatus egoSITTING)
					(NotNow)
				)
				(else
					(self changeState: 6)
				)
			)
		)
		(if (and spinachDipInRoom (Said 'eat/bread'))
			(cond 
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				((not (ego inRect: 73 100 106 104))
					(NotClose)
				)
				(else
					((inventory at: iSpinachDip) moveTo: -1)
					(aSpinachDip dispose:)
					(= spinachDipInRoom FALSE)
					(theGame changeScore: -5)
					(Print 35 31 #at 15 -1 #width 280 #draw)
				)
			)
		)
		(if (Said 'get/bread')
			(cond 
				((== spinachDipInRoom FALSE)
					(AlreadyTook)
				)
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				((not (ego inRect: 73 100 106 104))
					(NotClose)
				)
				(else
					(Print 35 32)
					(ego get: iSpinachDip)
					(theGame changeScore: 2)
					(aSpinachDip dispose:)
					(= spinachDipInRoom FALSE)
				)
			)
		)
	)
)

(instance shipScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aShip
					setCel: (if (aShip cel?) 0 else 1)
					posn: -30 63
					setMotion: MoveTo 333 64 self
				)
			)
			(1
				(= cycles (Random 30 50))
				(= state -1)
			)
		)
	)
)

(instance henchScript of Script
	(method (changeState newState)
		(if (== currentStatus egoSTOPPED) (return))
		(switch (= state newState)
			(1
				(= cycles (Random 50 100))
			)
			(2
				(if (!= currentStatus egoSITTING)
					(-- state)
					(= cycles (Random 50 100))
				else
					(aHench setMotion: MoveTo 157 107 self)
					(NotifyScript HENCHWOMAN 1)
					(= henchwomanIsHere TRUE)
				)
			)
			(3
				(aHench loop: 0)
				(Print 35 40 #draw #at -1 20)
				(Print 35 41 #at -1 130)
				(= seconds 5)
			)
			(4
				(Print 35 42)
				(aHench setMotion: MoveTo 155 234 self)
				(= henchwomanBeckons TRUE)
			)
			(5
				(= seconds 10)
			)
			(6
				(aHench dispose:)
				(= henchView 0)
				(= henchwomanIsHere FALSE)
				(= henchwomanBeckons FALSE)
			)
		)
	)
)

(instance aSpinachDip of View
	(properties
		y 79
		x 94
		view 315
		cel 2
		priority 4
		signal ignrAct
	)
)

(instance aGirl1 of PicView
	(properties
		y 144
		x 252
		view 315
		cel 5
		priority 11
		signal ignrAct
	)
)

(instance aGirl2 of PicView
	(properties
		y 123
		x 319
		view 315
		cel 5
		priority 11
		signal ignrAct
	)
)

(instance aMan1 of PicView
	(properties
		y 147
		x 201
		view 315
		cel 6
		priority 11
		signal ignrAct
	)
)

(instance aMan2 of PicView
	(properties
		y 137
		x 277
		view 315
		cel 6
		priority 11
		signal ignrAct
	)
)

(instance aBoat of PicView
	(properties
		y 62
		x -7
		view 315
		loop 6
		priority 0
		signal ignrAct
	)
)

(instance aGirl3 of PicView
	(properties
		y 127
		view 315
		loop 4
		priority 11
		signal ignrAct
	)
)

(instance aMan3 of PicView
	(properties
		y 132
		x 39
		view 315
		loop 5
		priority 11
		signal ignrAct
	)
)

(instance aStool of PicView
	(properties
		y 104
		x 165
		view 315
		cel 1
		priority 6
		signal ignrAct
	)
)

(instance aBar of PicView
	(properties
		y 79
		x 165
		view 315
		priority 2
		signal ignrAct
	)
)

(instance aBarGirl1 of PicView
	(properties
		y 103
		x 200
		view 315
		cel 3
		priority 6
		signal ignrAct
	)
)

(instance aBarGirl2 of PicView
	(properties
		y 101
		x 227
		view 315
		cel 4
		priority 6
		signal ignrAct
	)
)

(instance aHench of Actor
	(properties
		y 233
		x 155
	)
)

(instance aBartender of Prop
	(properties
		y 68
		x 183
		view 319
		signal ignrAct
	)
)

(instance aTV of Prop
	(properties
		y 42
		x 162
		view 315
		loop 7
		signal ignrAct
	)
)

(instance aGirl1drinking of Extra
	(properties
		y 100
		x 313
		view 315
		loop 1
	)
)

(instance aTitFeeler of Extra
	(properties
		y 104
		x 121
		view 315
		loop 2
	)
)

(instance aGirl2drinking of Extra
	(properties
		y 88
		x 19
		view 315
		loop 3
	)
)

(instance aGirl3drinking of Extra
	(properties
		y 144
		x 66
		view 315
		loop 4
	)
)

(instance aManDrinking of Extra
	(properties
		y 147
		x 113
		view 315
		loop 5
	)
)

(instance aShip of Actor
	(properties
		y 998
		x 999
		view 315
		signal ignrAct
	)
)
