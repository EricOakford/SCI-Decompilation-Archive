;;; Sierra Script 1.0 - (do not remove this comment)
(script# 129)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm129 0
)

(local
	drain
	[sewage 2]
)
(instance rm129 of Room
	(properties
		picture 203
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
				(cond 
					((== prevRoomNum 127) (if (<= (ego x?) 220) 55 else 133))
					((<= (ego x?) 120) 163)
					(else 292)
				)
			y: (if (== prevRoomNum 127) 87 else 180)
			init:
		)
		(HandsOn)
		(if (== prevRoomNum 132) (= methaneGasTimer 66))
		((= drain (Actor new:))
			view: 92
			loop: 5
			cel: 0
			posn: 122 153
			setPri: 0
			ignoreActors: 1
			init:
			addToPic:
		)
		((= [sewage 0] (Prop new:))
			view: 99
			loop: 2
			cel: 2
			posn: 96 113
			setPri: 0
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 1] (Prop new:))
			view: 99
			loop: 2
			cel: 1
			posn: 175 177
			setPri: 0
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		(if (< howFast 60) ([sewage 0] stopUpd:))
		(if (< howFast 30) ([sewage 1] stopUpd:))
		(sewerLight posn: 75 29 ignoreActors: 1 init: stopUpd:)
		(sewerLight2 posn: 159 59 ignoreActors: 1 init: stopUpd:)
		(cockroach
			view: 260
			setLoop: 0
			ignoreActors:
			init:
			setMotion: MoveTo 135 34 cockroach
		)
	)
	
	(method (doit)
		(cond 
			(sewerCutscene 0)
			(
			(and (not sewerCutscene) (ego inRect: 118 144 133 150)) (= sewerCutscene 1) (ego setScript: holeScript))
			((<= (ego y?) 85) (curRoom newRoom: 127))
			((>= (ego y?) 190)
				(cond 
					((>= methaneGasTimer 60) 0)
					((not wearingGasMask) (Print 129 0))
					(else (Print 129 1))
				)
				(= wearingGasMask 0)
				(= methaneGasTimer -1)
				(curRoom newRoom: 132)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'look<(in,down)[/hole]') (Print 129 2))
					((Said 'look/hole') (Print 129 3))
					(
					(Said 'move,press,cover,deposit/cover,manhole,lid') (Print 129 4))
					((Said 'go,enter,climb/hole') (Print 129 5))
				)
			)
		)
	)
)

(instance cockroach of Actor
	(properties
		y 90
		x 135
		yStep 1
		view 260
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(if
		(or (event claimed?) (!= (event type?) evSAID))
			(return)
		)
		(if (Said '/bug>')
			(cond 
				((> (cockroach x?) 200) (Print 129 6) (event claimed: 1))
				((Said 'look') (Print 129 7))
				((Said 'get') (Print 129 8))
			)
		)
	)
	
	(method (cue)
		(self posn: 0 1000)
	)
)

(instance holeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors: 1
					setLoop: (ego loop?)
					setPri: 0
					yStep: 8
					posn: 130 148
					setMotion: MoveTo 130 195 self
				)
			)
			(1
				(EgoDead
					{What a wimpy way to die....falling in a hole! Next time, be more careful.}
					298
					2
					0
				)
			)
		)
	)
)
