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
	aSpinachDip
	spinachDipInRoom
	canFollowHenchwoman
	aBartender
	aTV
	aGirl1drinking
	aTitFeeler
	aGirl2drinking
	aGirl3drinking
	aManDrinking
	aShip
	aHench
	local13
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
		(self setRegions: 300 setScript: rm35Script)
		(if ((inventory at: iSpinachDip) ownedBy: curRoomNum)
			(= spinachDipInRoom TRUE)
			((= aSpinachDip (View new:))
				ignoreActors:
				view: 315
				setLoop: 0
				setCel: 2
				setPri: 4
				posn: 94 79
				stopUpd:
				init:
			)
		else
			(self setRegions: HENCHWOMAN)
			(= henchView 316)
			(Load VIEW henchView)
			((= aHench (Actor new:))
				view: henchView
				posn: 155 233
				illegalBits: -32768
				setCycle: Walk
				init:
				setScript: henchScript
			)
		)
		((View new:)
			view: 315
			ignoreActors:
			loop: 0
			cel: 5
			posn: 252 144
			setPri: 11
			addToPic:
		)
		((View new:)
			view: 315
			ignoreActors:
			loop: 0
			cel: 5
			posn: 319 123
			setPri: 11
			addToPic:
		)
		((View new:)
			view: 315
			ignoreActors:
			loop: 0
			cel: 6
			posn: 201 147
			setPri: 11
			addToPic:
		)
		((View new:)
			view: 315
			ignoreActors:
			loop: 0
			cel: 6
			posn: 277 137
			setPri: 11
			addToPic:
		)
		((View new:)
			view: 315
			ignoreActors:
			loop: 6
			cel: 0
			posn: -7 62
			setPri: 0
			addToPic:
		)
		((View new:)
			view: 315
			ignoreActors:
			loop: 4
			cel: 0
			posn: 0 127
			setPri: 11
			addToPic:
		)
		((View new:)
			view: 315
			ignoreActors:
			loop: 5
			cel: 0
			posn: 39 132
			setPri: 11
			addToPic:
		)
		((View new:)
			view: 315
			ignoreActors:
			loop: 0
			cel: 1
			posn: 165 104
			setPri: 6
			addToPic:
		)
		((View new:)
			view: 315
			ignoreActors:
			loop: 0
			cel: 0
			posn: 165 79
			setPri: 2
			addToPic:
		)
		((View new:)
			view: 315
			ignoreActors:
			loop: 0
			cel: 3
			posn: 200 103
			setPri: 6
			addToPic:
		)
		((View new:)
			view: 315
			ignoreActors:
			loop: 0
			cel: 4
			posn: 227 101
			setPri: 6
			addToPic:
		)
		((= aBartender (Prop new:))
			view: 319
			ignoreActors:
			setLoop: 0
			setPri: 3
			posn: 183 68
			stopUpd:
			init:
		)
		((= aTV (Prop new:))
			view: 315
			ignoreActors:
			setLoop: 7
			setPri: 3
			posn: 162 42
			setCycle: Forward
			isExtra: TRUE
			init:
		)
		((= aGirl1drinking (Extra new:))
			view: 315
			loop: 1
			cel: 0
			posn: 313 100
			setPri: 7
			minPause: 20
			maxPause: 40
			minCycles: 5
			maxCycles: 10
			init:
		)
		((= aTitFeeler (Extra new:))
			view: 315
			loop: 2
			cel: 0
			posn: 121 104
			setPri: 6
			minPause: 20
			maxPause: 30
			minCycles: 5
			maxCycles: 8
			init:
		)
		((= aGirl2drinking (Extra new:))
			view: 315
			loop: 3
			cel: 0
			posn: 19 88
			setPri: 7
			minPause: 22
			maxPause: 50
			minCycles: 7
			maxCycles: 12
			init:
		)
		((= aGirl3drinking (Extra new:))
			view: 315
			loop: 4
			cel: 0
			posn: 66 144
			setPri: 11
			init:
		)
		((= aManDrinking (Extra new:))
			view: 315
			loop: 5
			cel: 0
			posn: 113 147
			setPri: 11
			minPause: 32
			maxPause: 70
			minCycles: 5
			maxCycles: 10
			init:
		)
		((= aShip (Actor new:))
			view: 315
			illegalBits: 0
			ignoreActors:
			setLoop: 6
			setCel: 0
			setPri: 0
			setStep: 1 1
			moveSpeed: 1
			posn: 999 998
			init:
			setScript: shipScript
		)
		(NormalEgo)
		(ego posn: 157 157 init:)
	)
)

(instance rm35Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0002)
			(if (== canFollowHenchwoman FALSE)
				(curRoom newRoom: 31)
			else
				(Print 35 0)
				(Print 35 1)
				(= currentStatus egoCAPTURED)
				(curRoom newRoom: 95)
			)
		)
		(if
		(and henchwomanIsHere local13 (> (ego y?) 146))
			(= local13 0)
			(= currentStatus egoCAPTURED)
			(= canFollowHenchwoman TRUE)
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
				(if
				(and (== spinachDipInRoom FALSE) (not (henchScript state?)))
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
				(NormalEgo 3)
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
			(9 (= seconds 2))
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
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if
			(and
				henchwomanIsHere
				(< (henchScript state?) 5)
				(Said '/bimbo>')
			)
			(cond 
				((Said 'call/') (Print (Format @str 35 3 introductoryPhrase)))
				((Said 'look/') (Print 35 4))
				(else (Print 35 5) (Print 35 6 #at -1 152))
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
			(if (Said '/agent') (Print 35 9))
			(if (Said '/buffet,ship,bimbo,children')
				(Print 35 10)
				(Print 35 11 #at -1 152)
			)
			(if (Said '/cup,craft') (Print 35 12) (Print 35 13))
			(if (Said '/burn') (Print 35 14))
			(if (Said '/computer,krod') (Print 35 15))
			(if (Said '/bottle,bar')
				(if (== currentStatus egoSITTING)
					(Print 35 16)
				else
					(Print 35 17)
				)
				(if spinachDipInRoom (Print 35 18))
			)
			(if (and spinachDipInRoom (Said '/bowl,bread')) (Print 35 19))
			(if (Said '[/airport]')
				(Print 35 20)
				(if spinachDipInRoom (Print 35 18))
			)
		)
		(if (Said 'bath[/down,barstool]')
			(cond 
				((== currentStatus egoSITTING) (YouAre))
				((!= currentStatus egoNORMAL) (NotNow))
				(
					(and
						(not (ego inRect: 148 103 222 109))
						(not (ego inRect: 64 107 285 128))
					)
					(Print 35 21)
				)
				(else (self changeState: 1))
			)
		)
		(if
			(or
				(Said 'new,(get<up),new[/up]')
				(Said 'disembark[/barstool]')
			)
			(cond 
				((== currentStatus egoNORMAL) (Print 35 22))
				((!= currentStatus egoSITTING) (NotNow))
				(else (self changeState: 4))
			)
		)
		(if (Said 'call/agent')
			(cond 
				((== currentStatus egoNORMAL) (Print 35 22))
				((!= currentStatus egoSITTING) (NotNow))
				(else
					(Print (Format @str 35 23 introductoryPhrase))
					(Print 35 24)
					(Print 35 25 #at -1 152)
				)
			)
		)
		(if (Said 'call/bimbo')
			(cond 
				((== currentStatus egoNORMAL) (Print 35 22))
				((!= currentStatus egoSITTING) (NotNow))
				(else
					(Print (Format @str 35 26 introductoryPhrase))
					(Print 35 27)
					(if (> filthLevel 10) (Print 35 28))
				)
			)
		)
		(if (Said 'call') (Print 35 29))
		(if (or (Said 'give/i/beer') (Said 'buy/beer'))
			(cond 
				((== currentStatus egoNORMAL) (Print 35 22))
				((!= currentStatus egoSITTING) (NotNow))
				(else (Print 35 30))
			)
		)
		(if (or (Said 'give/i/drink') (Said 'buy/drink'))
			(cond 
				((== currentStatus egoNORMAL) (Print 35 22))
				((!= currentStatus egoSITTING) (NotNow))
				(else (self changeState: 6))
			)
		)
		(if (and spinachDipInRoom (Said 'eat/bread'))
			(cond 
				((!= currentStatus 0) (NotNow))
				((not (ego inRect: 73 100 106 104)) (NotClose))
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
				((== spinachDipInRoom FALSE) (AlreadyTook))
				((!= currentStatus egoNORMAL) (NotNow))
				((not (ego inRect: 73 100 106 104)) (NotClose))
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
	(properties)
	
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
	(properties)
	
	(method (changeState newState)
		(if (== currentStatus egoSTOPPED) (return))
		(switch (= state newState)
			(1 (= cycles (Random 50 100)))
			(2
				(if (!= currentStatus 1009)
					(-- state)
					(= cycles (Random 50 100))
				else
					(aHench setMotion: MoveTo 157 107 self)
					(NotifyScript 8 1)
					(= henchwomanIsHere TRUE)
				)
			)
			(3
				(aHench loop: 0)
				(Print 35 40 #draw #at -1 20)
				(Print 35 41 #at -1 152)
				(= seconds 5)
			)
			(4
				(Print 35 42)
				(aHench setMotion: MoveTo 155 234 self)
				(= local13 1)
			)
			(5 (= seconds 10))
			(6
				(aHench dispose:)
				(= henchView 0)
				(= henchwomanIsHere FALSE)
				(= local13 0)
			)
		)
	)
)
