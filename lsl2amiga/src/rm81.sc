;;; Sierra Script 1.0 - (do not remove this comment)
(script# 81)
(include sci.sh)
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
	newBassSetter
)
(instance rm81 of Rm
	(properties
		picture 81
		horizon 1
		south 80
	)
	
	(method (init)
		(Load rsVIEW 726)
		(Load rsVIEW 181)
		(Load rsVIEW 182)
		(Load rsVIEW 183)
		(Load rsVIEW 31)
		(Load rsVIEW 30)
		(super init:)
		(aCoil cycleSpeed: 10 init: hide:)
		(aYellowName
			setPri: 11
			setCycle: Fwd
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
					(& (ego onControl: 1) $0002)
					(== throwingAtIce 0)
					(== currentStatus 0)
				)
				(= currentStatus 1017)
				(self changeState: 1)
			)
			(
				(and
					(& (ego onControl: 1) $0004)
					(== throwingAtIce 0)
					(== currentStatus 1017)
				)
				(= currentStatus 1018)
				(self changeState: 2)
			)
			(
				(and
					(& (ego onControl: 1) $0010)
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
					setCycle: Fwd
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
					setCycle: End
					setMotion: MoveTo 160 132 self
				)
			)
			(3
				(ego view: 181 setLoop: 2 cel: 0 setCycle: End self)
			)
			(4
				(Print 81 11 #draw)
				(= cycles 25)
			)
			(5
				(ego setLoop: 3 cel: 0 setCycle: End self)
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
				(aCoil posn: 153 118 cel: 0 setCycle: End self show:)
				(ego view: 182 setLoop: 0 setCycle: Fwd)
				(= cycles 10)
			)
			(10
				(if (== throwingAtIce 31)
					(ego put: 31 -1)
					(Print 81 12 #icon 31 0 0 #at -1 20)
				else
					(ego put: 30 -1)
					(Print 81 13 #icon 30 0 0 #at -1 20)
				)
			)
			(11
				(Print 81 14 #at -1 15 #width 280 #draw)
				(Print 81 15 #at -1 20)
				(Print 81 16 #at -1 20)
				(if (> filthLevel 10) (Print 81 17 #at -1 130))
				(aCoil hide:)
				(addToPics add: aCoil1 doit:)
				(ego view: 183 cel: 0 posn: 156 117 setCycle: End self)
			)
			(12
				(aCoil posn: 153 102 cel: 0 setCycle: End self show:)
				(ego view: 182 setCycle: Fwd)
			)
			(13
				(aCoil hide:)
				(addToPics add: aCoil2 doit:)
				(ego view: 183 cel: 0 posn: 156 101 setCycle: End self)
			)
			(14
				(aCoil posn: 153 86 cel: 0 setCycle: End self show:)
				(ego view: 182 setCycle: Fwd)
			)
			(15
				(aCoil hide:)
				(addToPics add: aCoil3 doit:)
				(ego view: 183 cel: 0 posn: 156 85 setCycle: End self)
			)
			(16
				(aCoil posn: 153 70 cel: 0 setCycle: End self show:)
				(ego view: 182 setCycle: Fwd)
			)
			(17
				(aCoil hide:)
				(addToPics add: aCoil4 doit:)
				(ego view: 183 cel: 0 posn: 156 69 setCycle: End self)
			)
			(18
				(Print 81 18 #draw)
				(Print 81 19 #at -1 130)
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
				(aYellowName show: setCycle: End self)
			)
			(21
				(NormalEgo)
				(aYellowName hide:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
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
				(ego has: 21)
				(Said 'conceal,drain,apply,conceal/bottle,rejuvenator')
			)
			(Print 81 5)
			(Print 81 6)
			(ego put: 21 -1)
			(theGame changeScore: -5)
		)
		(if (Said 'run,climb') (Print 81 7))
		(if (Said 'throw,apply/>')
			(cond 
				((not (ego has: 30)) (DontHave))
				((!= currentStatus 0) (NotNow))
				((not (ego inRect: 127 130 202 154)) (Print 81 8))
				(else
					(theGame changeScore: 10)
					(Ok)
					(= throwingAtIce 30)
					(self changeState: 8)
				)
			)
		)
		(if (Said 'throw,apply/beach')
			(cond 
				((not (ego has: 31)) (DontHave))
				((!= currentStatus 0) (NotNow))
				((not (ego inRect: 127 130 202 154)) (Print 81 8))
				(else
					(theGame changeScore: 10)
					(Ok)
					(= throwingAtIce 31)
					(self changeState: 8)
				)
			)
		)
	)
)

(instance aCoil1 of PV
	(properties
		y 118
		x 153
		view 726
		loop 1
		cel 4
		signal $4000
	)
)

(instance aCoil2 of PV
	(properties
		y 102
		x 153
		view 726
		loop 1
		cel 4
		signal $4000
	)
)

(instance aCoil3 of PV
	(properties
		y 86
		x 153
		view 726
		loop 1
		cel 4
		signal $4000
	)
)

(instance aCoil4 of PV
	(properties
		y 70
		x 153
		view 726
		loop 1
		cel 4
		signal $4000
	)
)

(instance aCoil of Prop
	(properties
		y 122
		x 151
		view 726
		loop 1
		signal $4000
	)
)

(instance aYellowName of Prop
	(properties
		y 136
		x 53
		view 726
		signal $4000
	)
)
