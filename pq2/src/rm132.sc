;;; Sierra Script 1.0 - (do not remove this comment)
(script# 132)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	rm132 0
)

(local
	[waste 3]
	local3
)
(instance rm132 of Room
	(properties
		picture 200
		style $0000
	)
	
	(method (init)
		(super init:)
		(self setRegions: 205)
		(ego
			view: (if (not gunDrawn) 0 else 6)
			x:
				(switch prevRoomNum
					(129
						(if (<= (ego x?) 200) 80 else 155)
					)
					(131 15)
				)
			y:
				(switch prevRoomNum
					(129 95)
					(131
						(if (<= (ego y?) 115) 95 else 135)
					)
				)
			init:
		)
		(HandsOn)
		(= local3 0)
		((Prop new:)
			view: 260
			loop: 8
			cel: 1
			posn: 311 182
			setPri: 1
			ignoreActors: 1
			init:
			stopUpd:
		)
		((= [waste 0] (Prop new:))
			view: 99
			loop: 2
			cel: 2
			posn: 210 189
			setPri: 0
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [waste 1] (Prop new:))
			view: 99
			loop: 1
			cel: 2
			posn: 65 124
			setPri: 0
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [waste 2] (Prop new:))
			view: 99
			loop: 2
			cel: 0
			posn: 163 153
			setPri: 0
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		(if (< howFast 60)
			([waste 0] stopUpd:)
			([waste 1] stopUpd:)
		)
		(if (< howFast 30) ([waste 2] stopUpd:))
		(sewerRat
			name: 4
			setLoop: 2
			illegalBits: 0
			posn: 70 66
			ignoreActors: 1
			init:
			setMotion: MoveTo -100 66 sewerRat
		)
		(sewerLight posn: 190 78 ignoreActors: 1 init: stopUpd:)
		(sewerLight2 posn: 91 54 ignoreActors: 1 init: stopUpd:)
	)
	
	(method (doit)
		(cond 
			(sewerCutscene 0)
			((<= (ego x?) 5) (curRoom newRoom: 131))
			((ego inRect: 70 70 165 94) (curRoom newRoom: 129))
			((ego inRect: 160 190 320 195)
				(if (not local3)
					(= local3 1)
					(Print 132 0)
					(ego setMotion: MoveTo (ego x?) 180)
				)
			)
			(else (= local3 0))
		)
		(super doit:)
	)
)
