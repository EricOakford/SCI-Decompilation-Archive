;;; Sierra Script 1.0 - (do not remove this comment)
(script# 127)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	rm127 0
)

(local
	[sewage 4]
)
(instance rm127 of Room
	(properties
		picture 200
		style $0000
	)
	
	(method (init)
		(super init:)
		(self setRegions: 205)
		(ego
			view:
				(cond 
					(wearingGasMask (if gunDrawn 306 else 296))
					(gunDrawn 6)
					(else 0)
				)
			x:
				(switch prevRoomNum
					(124
						(if (<= (ego x?) 200) 80 else 155)
					)
					(126 15)
					(129
						(if (<= (ego x?) 85) 190 else 285)
					)
				)
			y:
				(switch prevRoomNum
					(124 95)
					(126
						(if (<= (ego y?) 115) 95 else 135)
					)
					(129 185)
				)
			init:
		)
		(HandsOn)
		((= [sewage 0] (Prop new:))
			view: 99
			loop: 2
			cel: 2
			posn: 210 189
			setPri: 0
			setCycle: Forward
			cycleSpeed: 3
			ignoreActors: 1
			init:
		)
		((= [sewage 1] (Prop new:))
			view: 99
			loop: 0
			cel: 2
			posn: 65 124
			setPri: 0
			setCycle: Forward
			cycleSpeed: 3
			ignoreActors: 1
			init:
		)
		((= [sewage 2] (Prop new:))
			view: 99
			loop: 0
			cel: 2
			posn: 16 126
			setPri: 0
			setCycle: Forward
			cycleSpeed: 3
			ignoreActors: 1
			init:
		)
		(if (< howFast 60) ([sewage 0] stopUpd:))
		(if (< howFast 30)
			([sewage 1] stopUpd:)
			([sewage 2] stopUpd:)
		)
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
			((< (ego y?) 155)
				(if (!= methaneGasTimer -1)
					(cond 
						((> methaneGasTimer 60) 0)
						((not wearingGasMask) (Print 127 0))
						(else (Print 127 1))
					)
					(ego view: (if (not gunDrawn) 0 else 6))
					(= wearingGasMask 0)
					(= methaneGasTimer -1)
				)
			)
			((== methaneGasTimer -1) (= methaneGasTimer 65))
		)
		(cond 
			(sewerCutscene 0)
			((>= (ego y?) 190) (curRoom newRoom: 129))
			((<= (ego x?) 5) (curRoom newRoom: 126))
			((ego inRect: 70 70 165 94) (curRoom newRoom: 124))
		)
		(super doit:)
	)
)
