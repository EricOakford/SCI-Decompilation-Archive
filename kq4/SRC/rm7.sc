;;; Sierra Script 1.0 - (do not remove this comment)
(script# 7)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room7 0
)
(synonyms
	(fisherman fisherman fisherman fisherman man)
)

(local
	local0
	local1
	gEgoOnControl
	local3
	fisherman
	smoke
	[local6 2]
	ripple1
	ripple2
	ripple3
	i
	fishermanTimer
	fishermanX
)
(instance fallSound of Sound
	(properties
		number 51
	)
)

(instance dizzySound of Sound
	(properties
		number 80
	)
)

(instance doorSound of Sound
	(properties
		number 300
	)
)

(instance fisherCage of Cage
	(properties
		top 137
		bottom 149
		right 231
	)
)

(instance door of Prop
	(properties)
	
	(method (cue)
		(if (!= (door cel?) (door lastCel:))
			(self setCycle: EndLoop self)
		else
			(HandsOn)
			(curRoom newRoom: 42)
		)
	)
)

(instance waves of List
	(properties)
)

(instance wave1 of Prop
	(properties)
)

(instance wave2 of Prop
	(properties)
)

(instance wave3 of Prop
	(properties)
)

(instance Room7 of Room
	(properties
		picture 7
	)
	
	(method (init)
		(= north 1)
		(= south 13)
		(= east 8)
		(= west 95)
		(= horizon 86)
		(= isIndoors FALSE)
		(ego edgeHit: 0)
		(super init:)
		(if isNightTime (curRoom overlay: 107))
		(self setRegions: BEACH GULL)
		(Load VIEW 2)
		(Load VIEW 5)
		(Load VIEW 6)
		(Load VIEW 7)
		(Load VIEW 8)
		(Load VIEW 17)
		(Load VIEW 19)
		(Load VIEW 18)
		(Load SOUND 80)
		(Load SOUND 51)
		(Load SOUND 300)
		(wave1
			isExtra: TRUE
			view: 667
			loop: 0
			cel: 0
			posn: 98 84
			setPri: 0
			ignoreActors:
			cycleSpeed: 3
			init:
		)
		(wave2
			isExtra: TRUE
			view: 667
			loop: 1
			cel: 0
			posn: 97 109
			setPri: 0
			ignoreActors:
			cycleSpeed: 3
			init:
		)
		(wave3
			isExtra: TRUE
			view: 667
			loop: 2
			cel: 0
			posn: 79 136
			setPri: 0
			ignoreActors:
			cycleSpeed: 3
			init:
		)
		(= ripple1 (Prop new:))
		(= ripple2 (Prop new:))
		(= ripple3 (Prop new:))
		(ripple1
			isExtra: TRUE
			view: 667
			loop: 3
			cel: 0
			posn: 11 168
			setPri: 0
			ignoreActors:
			setCycle: Forward
			cycleSpeed: 2
			init:
		)
		(ripple2
			isExtra: 1
			view: 667
			loop: 5
			cel: 0
			posn: 103 166
			setPri: 0
			ignoreActors:
			setCycle: Forward
			cycleSpeed: 2
			init:
		)
		(ripple3
			isExtra: 1
			view: 667
			loop: 4
			cel: 0
			posn: 117 177
			setPri: 0
			ignoreActors:
			setCycle: Forward
			cycleSpeed: 2
			init:
		)
		(waves add: wave1 wave2 wave3)
		(wave1 setScript: waveActions)
		(= smoke (Prop new:))
		(smoke
			isExtra: TRUE
			view: 625
			loop: 1
			cel: 0
			x: 200
			y: 19
			setCycle: Forward
			cycleSpeed: 1
			init:
		)
		(door
			view: 601
			loop: 0
			cel: 0
			x: 220
			y: 139
			setPri: 9
			ignoreActors:
			init:
			stopUpd:
		)
		(if (== fishermanState fisherComingHome)
			(if
				(<
					(= fishermanTimer
						(+
							(* 3600 (- gameHours hourMetFisherman))
							(* 60 (- gameMinutes minuteMetFisherman))
							(- gameSeconds secondMetFisherman)
						)
					)
					40
				)
				((= fisherman (Actor new:)) view: 230)
				(cond 
					((<= fishermanTimer 12) (= fishermanX 40))
					((< fishermanTimer 20) (= fishermanX 120))
					(else (= fishermanX 166))
				)
				(fisherman
					posn: fishermanX 144
					setPri: 13
					loop: 0
					setCycle: Walk
					xStep: 3
					yStep: 1
					setScript: fishWalkHouse
					init:
				)
				(fisherman baseSetter: (ScriptID 0 1))
			else
				(= fishermanState fisherAtHome)
			)
		)
		(ego observeControl: -32768 16384 1024)
		(if (<= (ego y?) (+ horizon 1))
			(ego y: (+ horizon 2))
		)
		(if
			(or
				(== prevRoomNum 0)
				(== prevRoomNum 8)
				(== (ego view?) 2)
			)
			(= currentStatus egoNormal)
		)
		(switch currentStatus
			(egoNormal
				(switch prevRoomNum
					(13
						(if (< (ego x?) 164)
							(ego posn: 170 188)
						else
							(ego posn: 304 187)
						)
					)
					(1
						(if (> (ego x?) 277)
							(ego posn: 143 (+ horizon 2))
						else
							(ego posn: 113 (+ horizon 2))
						)
					)
					(42
						(ego view: 2 loop: 1 posn: 212 140 xStep: 3 yStep: 2)
					)
					(95
						(if (== global107 9)
							(ego
								view: 2
								illegalBits: -32768
								observeControl: 128 1024
								posn: 1 143
							)
						else
							(ego posn: 1 (ego y?))
						)
					)
					(8
						(if (< (ego y?) 125) (ego x: 280) else (ego x: 317))
						(if (<= (ego y?) (+ horizon 1))
							(ego y: (+ horizon 2))
						)
						(if (ego inRect: 193 129 319 157) (ego setPri: 12))
					)
				)
			)
			(egoInShallowWater
				(switch prevRoomNum
					(13 (ego posn: 189 185))
					(1
						(ego posn: 93 (+ horizon 2))
					)
				)
			)
			(egoInKneeDeepWater
				(switch prevRoomNum
					(13 (ego posn: 137 185))
					(1
						(ego posn: 88 (+ horizon 2))
					)
				)
			)
			(egoInWaistDeepWater
				(switch prevRoomNum
					(13 (ego posn: 105 184))
					(1
						(ego posn: 73 (+ horizon 2))
					)
				)
			)
			(egoSwimming
				(switch prevRoomNum
					(95 (ego x: 2))
					(13 (ego posn: 42 184))
					(1
						(ego posn: 20 (+ horizon 2))
					)
				)
			)
		)
		(ego xStep: 3 yStep: 2 init: viewer: water)
		(ego y: (& (ego y?) $fffe))
		(fisherCage init:)
	)
	
	(method (doit)
		(super doit:)
		(if (not (ego inRect: -5 135 174 153)) (= global107 0))
		(= gEgoOnControl (ego onControl:))
		(if
		(and (ego inRect: -3 0 27 189) (!= global107 9))
			(ego view: 8)
		)
		(if
		(and (& (ego onControl: 1) $0040) (== global107 9))
			(ego observeControl: 16384)
			(ego ignoreControl: 128)
			(ego setPri: -1)
			(= global107 0)
		)
		(if
		(and (& (ego onControl: 1) $0020) (!= global107 9))
			(ego setPri: 13)
			(ego illegalBits: -32768 observeControl: 1024)
			(ego observeControl: 128)
			(= global107 9)
		)
		(if (== (curRoom script?) 0)
			(if (!= global107 9)
				(cond 
					((ego inRect: 103 157 219 189) (ego setPri: 15))
					((ego inRect: 193 129 319 157) (ego setPri: 12))
					((ego inRect: 80 125 157 158) (ego setPri: 8))
					(else (ego setPri: -1))
				)
			)
			(cond 
				(
					(and
						(== currentStatus egoNormal)
						(& gEgoOnControl $1000)
						(> (ego heading?) 135)
					)
					(self setScript: fallRocks)
				)
				(
					(and
						(== currentStatus egoNormal)
						(& gEgoOnControl $1000)
						(<= (ego heading?) 135)
					)
					(ego setPri: -1)
				)
				((& gEgoOnControl $2000)
					(if
						(and
							(!= global107 9)
							(> (ego heading?) 90)
							(< (ego heading?) 270)
						)
						(ego setPri: -1)
					else
						(ego setPri: 12)
					)
				)
				((& gEgoOnControl $0100)
					(if (!= global107 9)
						(if
						(and (> (ego heading?) 90) (< (ego heading?) 270))
							(ego setPri: 12)
						else
							(ego setPri: -1)
						)
					)
				)
				((== global107 9)
					(cond 
						((& gEgoOnControl $0010) (self setScript: fallNorth))
						((& gEgoOnControl $0004) (self setScript: fallSouth))
					)
				)
			)
		)
	)
	
	(method (dispose)
		(ego setPri: -1)
		(waves dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'get/fisherman') (if (== fishermanState fisherComingHome) (Print 7 0) else (Print 7 1)))
					((Said 'get/pole')
						(if (cast contains: fisherman)
							(Print 7 2)
						else
							(Print 7 3)
						)
					)
					((Said 'rob/pole')
						(if (cast contains: fisherman)
							(Print 7 4)
						else
							(Print 7 3)
						)
					)
					((Said 'kiss') (if (== fishermanState fisherComingHome) (Print 7 5) else (Print 7 6)))
					((Said 'deliver>')
						(if (= inventorySaidMe (inventory saidMe:))
							(if (ego has: (inventory indexOf: inventorySaidMe))
								(if (== fishermanState fisherComingHome) (Print 7 7) else (Print 7 8))
							else
								(DontHave)
							)
						)
					)
					((Said 'help/fisherman') (Print 7 9))
					(
						(or
							(Said 'look/around')
							(Said 'look/room')
							(Said 'look[<around][/!*]')
						)
						(Print 7 10)
					)
					((Said 'look>')
						(cond 
							((Said '/anchor') (Print 7 11))
							((Said '/cottage') (Print 7 12))
							((Said '<under/dock') (Print 7 13))
							((Said '/door') (Print 7 14))
							((Said '/window')
								(if
									(or
										(ego inRect: 185 126 207 140)
										(ego inRect: 230 141 256 153)
										(ego inRect: 270 140 315 151)
										(ego inRect: 204 129 233 148)
									)
									(Print 7 15)
								else
									(Print 800 1)
								)
							)
							((Said '/dock') (Print 7 16))
							((Said '/fisherman')
								(if (== fishermanState fisherComingHome)
									(Print 7 17)
								else
									(Print 7 3)
								)
							)
							((Said '/grass') (Print 7 18))
						)
					)
					((Said 'get/anchor') (Print 7 19))
					((Said 'break/window') (Print 7 20))
					((Said 'break/door') (Print 7 21))
					((Said 'open/window') (Print 7 22))
					((Said 'close/door') (Print 7 23))
					(
						(Said
							'converse[/fisherman,fisherman,fisherman,fisherman,fisherman]'
						)
						(if (== fishermanState fisherComingHome)
							(Print 7 24)
						else
							(event claimed: FALSE)
						)
					)
					((Said 'unlatch/door') (if (not isNightTime) (Print 7 25) else (Print 7 26)))
					((Said 'bang/door')
						(if (ego inRect: 204 129 233 148)
							(if (not isNightTime) (Print 7 27) else (Print 7 28))
						else
							(Print 800 1)
						)
					)
					((Said 'open/door')
						(cond 
							((!= (door cel?) 0) (Print 7 29))
							((ego inRect: 204 129 233 148)
								(if (not isNightTime)
									(HandsOff)
									(ego loop: 0)
									(doorSound play: door)
									(door ignoreActors: setCycle: EndLoop)
								else
									(Print 7 26)
								)
							)
							(else (Print 800 1))
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(HandsOn)
		(ego setPri: -1 illegalBits: -32768)
		(if (== fishermanState fisherComingHome) (= fishermanState fisherAtHome))
		(super newRoom: newRoomNumber)
	)
)

(instance water of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(!= local0 (= local1 (ego onControl: TRUE)))
				(!= global107 9)
			)
			(= local0 local1)
			(ego setCycle: Walk)
			(switch local1
				(1
					(= currentStatus egoNormal)
					(ego view: 2)
				)
				(2048
					(ego observeControl: 16384)
					(= currentStatus egoInShallowWater)
					(ego view: 5)
				)
				(512
					(= currentStatus egoInKneeDeepWater)
					(ego view: 6)
				)
				(8
					(ego view: 7)
					(= currentStatus egoInWaistDeepWater)
				)
				(2
					(ego view: 8)
					(= currentStatus egoSwimming)
					(ego setCycle: Forward)
				)
			)
		)
	)
)

(instance fallSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(fallSound play:)
				(ego
					viewer: 0
					illegalBits: 0
					yStep: 9
					setMotion: MoveTo (ego x?) (+ (ego y?) 35) self
					view: 17
					cel: 255
					setCycle: EndLoop
				)
			)
			(1
				(= global107 0)
				(ego yStep: 2 setPri: -1)
				(ego
					view: 19
					loop: (& (ego loop?) $0001)
					cel: 255
					setCycle: EndLoop self
				)
			)
			(2
				(HandsOn)
				(ego
					viewer: water
					illegalBits: -32768
					observeControl: 1024
				)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance fallNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(fallSound play:)
				(ego
					viewer: 0
					illegalBits: 0
					setPri: 11
					posn: (ego x?) (- (ego y?) 5)
					yStep: 8
					view: 17
					cel: 255
					setCycle: EndLoop
					setMotion: MoveTo (ego x?) (+ (ego y?) 10) self
				)
			)
			(1
				(ego
					yStep: 2
					setPri: -1
					loop: 3
					view: 7
					illegalBits: -32768
					observeControl: 1024
					viewer: water
				)
				(= global107 0)
				(curRoom setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance fallRocks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					viewer: 0
					view: 17
					illegalBits: 0
					setPri: 14
					xStep: 10
					yStep: 4
					setCycle: Forward
					setMotion: MoveTo 173 186 self
				)
				(fallSound play:)
			)
			(1
				(ego xStep: 3 yStep: 2 view: 18 loop: 0 setCycle: Forward)
				(= global107 0)
				(= seconds 5)
				(dizzySound play:)
			)
			(2
				(ego
					illegalBits: -32768
					observeControl: 1024
					setCycle: Walk
					loop: 1
					view: 2
					cycleSpeed: 0
					viewer: water
				)
				(curRoom setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance fishWalkHouse of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fisherman setMotion: MoveTo 207 140 self)
			)
			(1
				(fisherman setPri: 10)
				(door setCycle: EndLoop self)
				(doorSound play:)
			)
			(2
				(fisherman setMotion: MoveTo 226 134 self)
			)
			(3
				(fisherman setMotion: MoveTo 255 134 self)
			)
			(4
				(door ignoreActors: 0 setCycle: BegLoop self)
				(= fishermanState 3)
				(fisherman dispose:)
			)
			(5 (door stopUpd:))
		)
	)
)

(instance waveActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= i 0)
				(while (< i (waves size?))
					((View new:)
						view: ((waves at: i) view?)
						loop: ((waves at: i) loop?)
						cel: 255
						setPri: 0
						ignoreActors:
						x: ((waves at: i) x?)
						y: ((waves at: i) y?)
						init:
						addToPic:
						yourself:
					)
					(++ i)
				)
				(= i 0)
				(self changeState: 1)
			)
			(1
				((waves at: i) cel: 255 show: setCycle: EndLoop self)
			)
			(2
				((waves at: i) hide:)
				(if (< i (- (waves size?) 1))
					(++ i)
				else
					(= i 0)
				)
				(waveActions changeState: 1)
			)
		)
	)
)
