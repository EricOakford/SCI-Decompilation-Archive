;;; Sierra Script 1.0 - (do not remove this comment)
(script# INN) ;2
(include game.sh)
(use Main)
(use Flame)
(use Intrface)
(use LoadMany)
(use StopWalk)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	innRoom 0
)

(procedure (Face actor1 actor2)
	(DirLoop
		actor1
		(GetAngle
			(actor1 x?)
			(actor1 y?)
			(actor2 x?)
			(actor2 y?)
		)
	)
	(if (== argc 3)
		(DirLoop
			actor2
			(GetAngle
				(actor2 x?)
				(actor2 y?)
				(actor1 x?)
				(actor1 y?)
			)
		)
	)
)

(instance innRoom of Room
	(properties
		picture rKattaInn
		style IRISIN
	)
	
	(method (init)
		(Load SCRIPT FLAME)
		(LoadMany VIEW vEgo vEgoStanding rKattaInn vShameen vMusician)
		(LoadMany SOUND rKattaInn rKhaveenHouse rShemaDance)
		(super init:)
		(egoTable init:)
		(table1 init:)
		(table2 init:)
		(table3 init:)
		(table4 init:)
		(trim1 init:)
		(trim2 init:)
		(oilLamp init:)
		(brassBottle init:)
		(shameen init:)
		(rope init:)
		(tassle init:)
		(egoPillow init:)
		(pillow1 init:)
		(pillow2 init:)
		(pillow3 init:)
		(detail1 init:)
		(detail2 init:)
		(flame1 init:)
		(flame2 init:)
		(musician init:)
		(addToPics doit:)
		(self setScript: rmScript)
	)
)

(instance rmScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalSound number: rKattaInn loop: 1 play: self)
				(Print INN 0
					#at -1 12
					#dispose
				)
				(ego
					loop: 0
					cel: 0
					posn: 153 230
					init:
					setCycle: StopWalk 4
					setMotion: MoveTo 153 180 self
				)
			)
			(1
				(Face ego shameen)
				(= cycles 2)
			)
			(2 (shameen setCycle: EndLoop self))
			(3 (= cycles 4))
			(4
				(shameen setLoop: 1 setCel: 0)
				(= cycles 4)
			)
			(5
				(shameen setCel: 1)
				(= cycles 4)
			)
			(6
				(shameen setCel: 0)
				(= cycles 10)
			)
			(7
				(cls)
				(globalSound fade:)
				(client newRoom: 3)
			)
		)
	)
)

(instance detail1 of PicView
	(properties
		x 61
		y 41
		view rKattaInn
		loop 1
		cel 2
	)
)

(instance detail2 of PicView
	(properties
		x 116
		y 43
		view rKattaInn
		loop 1
		cel 3
	)
)

(instance pillow1 of PicView
	(properties
		x 99
		y 135
		view rKattaInn
		loop 2
		cel 6
	)
)

(instance pillow2 of PicView
	(properties
		x 95
		y 117
		view rKattaInn
		loop 2
		cel 7
		priority 6
	)
)

(instance pillow3 of PicView
	(properties
		x 38
		y 128
		view rKattaInn
		loop 2
		cel 8
		priority 6
	)
)

(instance egoPillow of PicView
	(properties
		x 26
		y 180
		view rKattaInn
		loop 2
		cel 4
	)
)

(instance tassle of PicView
	(properties
		x 92
		y 67
		view rKattaInn
		loop 2
		cel 3
	)
)

(instance rope of PicView
	(properties
		x 168
		y 66
		view rKattaInn
		loop 2
		cel 2
	)
)

(instance shameen of Prop
	(properties
		x 252
		y 160
		view vShameen
		cycleSpeed 2
	)
)

(instance egoTable of PicView
	(properties
		x 54
		y 180
		view rKattaInn
	)
)

(instance table4 of PicView
	(properties
		x 17
		y 127
		view rKattaInn
		cel 1
	)
)

(instance table2 of PicView
	(properties
		x 76
		y 117
		view rKattaInn
		cel 3
	)
)

(instance table3 of PicView
	(properties
		x 128
		y 111
		view rKattaInn
		cel 4
	)
)

(instance table1 of PicView
	(properties
		x 132
		y 139
		view rKattaInn
		cel 1
	)
)

(instance oilLamp of PicView
	(properties
		x 279
		y 121
		view rKattaInn
		loop 2
		priority 15
	)
)

(instance brassBottle of PicView
	(properties
		x 297
		y 183
		view rKattaInn
		loop 2
		cel 1
	)
)

(instance trim1 of PicView
	(properties
		x 200
		y 34
		view rKattaInn
		loop 1
	)
)

(instance trim2 of PicView
	(properties
		x 272
		y 18
		view rKattaInn
		loop 1
		cel 1
		priority 12
	)
)

(instance musician of Actor
	(properties
		x 198
		y 111
		view vMusician
	)
)

(instance flame1 of Flame
	(properties
		x 54
		y 147
		view rKattaInn
		loop 3
		priority 6
		signal fixPriOn
	)
)

(instance flame2 of Flame
	(properties
		x 133
		y 111
		view rKattaInn
		loop 3
		priority 7
		signal fixPriOn
	)
)
