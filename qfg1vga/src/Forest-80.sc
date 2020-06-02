;;; Sierra Script 1.0 - (do not remove this comment)
(script# 80)
(include game.sh) (include "80.shm")
(use Main)
(use RandomEncounter)
(use Procs)
(use PolyPath)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm80 0
)

(local
	local0
)
(instance rm80 of EncRoom
	(properties
		picture 701
		style DISSOLVE
		horizon 57
		encChance 20
		entrances (| reNORTH reEAST reWEST)
		illBits -28672
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(NormalEgo)
		(southBush addToPic:)
		(if
			(not
				(OneOf prevRoomNum
					vBear vMinotaur vSaurus vMantray vCheetaur
					vGoblin vOgre vTroll vDragon vBrigand vBrigandLeader
				)
			)
			(= egoX (ego x?))
			(= egoY (ego y?))
		)
		(if (> brunoTimer 260)
			(self encChance: 0)
			(= monsterHealth (= monsterNum 0))
			(User canInput: 0)
			(ChangeGait MOVE_WALK 0)
			(switch prevRoomNum
				(79
					(ego setScript: egoDiesFrom79)
				)
				(81
					(ego setScript: egoDiesFrom81)
				)
				(else 
					(ego setScript: egoLoses)
				)
			)
		)
		(self setRegions: ENCOUNTER)
	)
	
	(method (dispose)
		(Bset fBeenIn80)
		(super dispose:)
	)
)

(instance bruno of Actor
	(properties
		x -7
		y 114
		noun N_BRUNO
		view 65
	)
)

(instance knife1 of Actor
	(properties
		view 65
	)
)

(instance knife2 of Actor
	(properties
		view 65
	)
)

(instance southBush of View
	(properties
		x 58
		y 167
		noun N_SOUTHBUSH
		view 700
		loop 3
		priority 15
	)
)

(instance egoLoses of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bruno init: loop: 0)
				(ego setMotion: PolyPath 160 80 self)
			)
			(1
				(bruno setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(2
				(ego setCycle: 0 setMotion: 0)
				(knife1
					illegalBits: 0
					setLoop: 7
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					moveSpeed: 1
					cycleSpeed: 4
					setCycle: Forward
					setPri: (+ (ego priority?) 1)
					posn: (+ (bruno x?) (* local0 41)) (- (bruno y?) 24)
					setMotion: MoveTo (ego x?) (- (ego y?) 22) self
				)
				(bruno
					setLoop: 0
					setCel: 0
					posn: 11 114
					setCycle: Walk
					setMotion: PolyPath 31 131
				)
			)
			(3
				(knife1 setCycle: 0 setCel: 3 setMotion: 0)
				(bruno posn: 31 131 cel: 0 setLoop: 5 setCycle: EndLoop)
				(knife2
					illegalBits: 0
					setLoop: 7
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					moveSpeed: 1
					cycleSpeed: 4
					setCycle: Forward
					setPri: (+ (ego priority?) 1)
					posn: (+ (bruno x?) (* 41 local0)) (- (bruno y?) 27)
					setMotion: MoveTo (ego x?) (- (ego y?) 25) self
				)
			)
			(4
				(bruno
					setLoop: 0
					setCel: 0
					posn: 49 130
					setCycle: Walk
					setMotion: PolyPath 118 116
				)
				(knife1 dispose:)
				(knife2 setCycle: 0 setCel: 3 setMotion: 0)
				(= ticks 60)
			)
			(5
				(knife2 dispose:)
				(ego
					view: 516
					setLoop: 3
					cycleSpeed: 1
					setMotion: 0
					setCycle: EndLoop
				)
				(= ticks 240)
			)
			(6
				(bruno setMotion: 0 setCel: 0)
				(EgoDead 31 32)
			)
		)
	)
)

(instance egoDiesFrom79 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bruno
					init:
					posn: 140 128
					setLoop: 1
					setCycle: Walk
					setMotion: PolyPath 122 125
				)
				(ego setMotion: MoveTo 54 128 self)
			)
			(1
				(bruno setMotion: PolyPath 118 125 self)
			)
			(2
				(knife1
					illegalBits: 0
					setLoop: 7
					setCel: 1
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					moveSpeed: 1
					cycleSpeed: 4
					posn: 88 90
					setMotion: MoveTo 61 96 self
				)
			)
			(3 (EgoDead 31 32))
		)
	)
)

(instance egoDiesFrom81 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bruno init: posn: 8 114 loop: 0)
				(ego setMotion: PolyPath 236 115 self)
			)
			(1
				(bruno setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(2
				(ego setCycle: 0 setMotion: 0)
				(knife1
					illegalBits: 0
					setLoop: 7
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					moveSpeed: 1
					cycleSpeed: 4
					setCycle: Forward
					setPri: (+ (ego priority?) 1)
					posn: 14 90
					setMotion: MoveTo 229 82 self
				)
				(bruno
					setLoop: 0
					setCel: 0
					posn: 11 114
					setCycle: Walk
					setMotion: PolyPath 31 131
				)
			)
			(3
				(knife1 setCycle: 0 setCel: 0 setMotion: 0 posn: 233 82)
				(bruno posn: 31 131 cel: 0 setLoop: 5 setCycle: EndLoop)
				(knife2
					illegalBits: 0
					setLoop: 7
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					moveSpeed: 1
					cycleSpeed: 4
					setCycle: Forward
					setPri: (+ (ego priority?) 1)
					posn: 34 90
					setMotion: MoveTo 229 82 self
				)
			)
			(4
				(bruno
					setLoop: 0
					setCel: 0
					posn: 49 130
					setCycle: Walk
					setMotion: PolyPath 118 116
				)
				(knife1 dispose:)
				(knife2 setCycle: 0 setCel: 0 setMotion: 0 posn: 233 84)
				(= ticks 60)
			)
			(5
				(knife2 dispose:)
				(ego
					view: 516
					setLoop: 3
					cycleSpeed: 1
					setMotion: 0
					setCycle: EndLoop
				)
				(= ticks 240)
			)
			(6
				(bruno setMotion: 0 setCel: 0)
				(EgoDead 31 32)
			)
		)
	)
)
