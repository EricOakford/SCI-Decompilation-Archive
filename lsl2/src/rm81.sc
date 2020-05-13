;;; Sierra Script 1.0 - (do not remove this comment)
(script# 81)
(include game.sh)
(use Main)
(use BassSetter)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm81 0
)

(local
	local0
	throwingAtIce
	local2
	aCoil
	aYellowName
	newBassSetter
)
(instance rm81 of Room
	(properties
		picture 81
		horizon 1
		south 80
	)
	
	(method (init)
		(Load VIEW 726)
		(Load VIEW 181)
		(Load VIEW 182)
		(Load VIEW 183)
		(Load VIEW 31)
		(Load VIEW 30)
		(super init:)
		((= aCoil (Prop new:))
			view: 726
			setLoop: 1
			posn: 151 122
			cycleSpeed: 10
			ignoreActors:
			init:
			hide:
		)
		((= aYellowName (Prop new:))
			view: 726
			setLoop: 0
			setCel: 0
			setPri: 11
			posn: 53 136
			setCycle: Forward
			ignoreActors:
			cycleSpeed: 2
			init:
			hide:
		)
		(NormalEgo)
		(ego posn: 155 185 init:)
		(self setRegions: 700 setScript: rm81Script)
	)
)

(instance rm81Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(& (ego onControl: origin) $0002)
					(== throwingAtIce 0)
					(== currentStatus egoNORMAL)
				)
				(= currentStatus 1017)
				(self changeState: 1)
			)
			(
				(and
					(& (ego onControl: origin) $0004)
					(== throwingAtIce 0)
					(== currentStatus 1017)
				)
				(= currentStatus 1018)
				(self changeState: 2)
			)
			(
				(and
					(& (ego onControl: origin) $0010)
					(== throwingAtIce 0)
					(== currentStatus 1017)
				)
				(= currentStatus 1018)
				(self changeState: 7)
			)
			(
				(and
					(== local2 0)
					(== 0 (ego loop?))
					(ego inRect: 34 161 37 165)
				)
				(= local2 1)
				(self changeState: 20)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(Print 81 9 #at -1 20)
				(ego
					view: 181
					setLoop: 0
					setCycle: Forward
					setStep: 1 1
					baseSetter: (= newBassSetter (BassSetter new:))
				)
			)
			(2
				(Print 81 10 #at -1 20)
				(HandsOff)
				(ego
					setStep: 1 5
					setLoop: 1
					cel: 0
					setCycle: EndLoop
					setMotion: MoveTo 160 132 self
				)
			)
			(3
				(ego view: 181 setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(4
				(Print 81 11 #draw)
				(= cycles 25)
			)
			(5
				(ego setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(6
				(NormalEgo 0)
				(self changeState: 7)
			)
			(7
				(ego baseSetter: 0)
				(NormalEgo)
				(if newBassSetter (newBassSetter dispose:))
			)
			(8
				(= currentStatus 1019)
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 156 133 self
				)
			)
			(9
				(aCoil posn: 153 118 cel: 0 setCycle: EndLoop self show:)
				(ego view: 182 setLoop: 0 setCycle: Forward)
				(= cycles 10)
			)
			(10
				(if (== throwingAtIce iSand)
					(ego put: iSand -1)
					(Print 81 12 #icon 31 0 0 #at -1 20)
				else
					(ego put: iAshes -1)
					(Print 81 13 #icon 30 0 0 #at -1 20)
				)
			)
			(11
				(Print 81 14 #at -1 15 #width 280 #draw)
				(Print 81 15 #at -1 20)
				(Print 81 16 #at -1 20)
				(if (> filthLevel 10) (Print 81 17 #at -1 152))
				(aCoil hide:)
				((View new:)
					view: 726
					loop: 1
					cel: 4
					posn: 153 118
					ignoreActors:
					addToPic:
				)
				(ego view: 183 cel: 0 posn: 156 117 setCycle: EndLoop self)
			)
			(12
				(aCoil posn: 153 102 cel: 0 setCycle: EndLoop self show:)
				(ego view: 182 setCycle: Forward)
			)
			(13
				(aCoil hide:)
				((View new:)
					view: 726
					loop: 1
					cel: 4
					posn: 153 102
					ignoreActors:
					addToPic:
				)
				(ego view: 183 cel: 0 posn: 156 101 setCycle: EndLoop self)
			)
			(14
				(aCoil posn: 153 86 cel: 0 setCycle: EndLoop self show:)
				(ego view: 182 setCycle: Forward)
			)
			(15
				(aCoil hide:)
				((View new:)
					view: 726
					loop: 1
					cel: 4
					posn: 153 86
					ignoreActors:
					addToPic:
				)
				(ego view: 183 cel: 0 posn: 156 85 setCycle: EndLoop self)
			)
			(16
				(aCoil posn: 153 70 cel: 0 setCycle: EndLoop self show:)
				(ego view: 182 setCycle: Forward)
			)
			(17
				(aCoil hide:)
				((View new:)
					view: 726
					loop: 1
					cel: 4
					posn: 153 70
					ignoreActors:
					addToPic:
				)
				(ego view: 183 cel: 0 posn: 156 69 setCycle: EndLoop self)
			)
			(18
				(Print 81 18 #draw)
				(Print 81 19 #at -1 152)
				(Print 81 20)
				(ego
					view: 100
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 156 2 self
				)
			)
			(19
				(NormalEgo)
				(curRoom newRoom: 181)
			)
			(20
				(= local2 1)
				(ego setMotion: 0)
				(HandsOff)
				(aYellowName show: setCycle: EndLoop self)
			)
			(21
				(NormalEgo)
				(aYellowName hide:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/glacier,glacier') (Print 81 0))
			(if (Said '/carpet,carpet') (Print 81 1))
			(if (Said '[/airport,island,/,glacier,glacier]')
				(Print 81 2)
				(Print 81 3)
				(Print 81 4)
			)
		)
		(if
			(and
				(ego has: iHairRejuvenator)
				(Said 'conceal,drain,apply,conceal/bottle,rejuvenator')
			)
			(Print 81 5)
			(Print 81 6)
			(ego put: iHairRejuvenator -1)
			(theGame changeScore: -5)
		)
		(if (Said 'run,climb') (Print 81 7))
		(if (Said 'throw,apply/>')
			(cond 
				((not (ego has: iAshes)) (DontHave))
				((!= currentStatus egoNORMAL) (NotNow))
				((not (ego inRect: 127 130 202 154)) (Print 81 8))
				(else
					(theGame changeScore: 10)
					(Ok)
					(= throwingAtIce iAshes)
					(self changeState: 8)
				)
			)
		)
		(if (Said 'throw,apply/beach')
			(cond 
				((not (ego has: iSand)) (DontHave))
				((!= currentStatus egoNORMAL) (NotNow))
				((not (ego inRect: 127 130 202 154)) (Print 81 8))
				(else
					(theGame changeScore: 10)
					(Ok)
					(= throwingAtIce iSand)
					(self changeState: 8)
				)
			)
		)
	)
)
