;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room4 0
)
(synonyms
	(giantess giantess giantess giantess giantess woman giantess)
)

(enum
	ogressNotHere
	ogressAppears
	ogressChases
)

(local
	local0
	aOgress
	local2
	ogressState
	local4
	newActHeading
	aDeer
	local7
)
(instance doorSound of Sound
	(properties)
)

(instance ogressTheme of Sound
	(properties)
)

(instance ogreTheme of Sound
	(properties)
)

(instance door of Prop
	(properties)
	
	(method (cue)
		(if (!= (door cel?) (door lastCel:))
			(self setCycle: EndLoop self)
		else
			(HandsOn)
			(curRoom newRoom: 49)
		)
	)
)

(instance Room4 of Room
	(properties
		picture 4
	)
	
	(method (init)
		(= north 28)
		(= south 10)
		(= east 5)
		(= west 3)
		(= horizon 75)
		(= noWearCrown 1)
		(= isIndoors FALSE)
		(ego edgeHit: 0)
		(super init:)
		(if isNightTime (curRoom overlay: 104))
		(self setRegions: FOREST OGRE)
		(if (and (== gamePhase getTheHen) (== ogressIsHome FALSE))
			(Load VIEW 245)
			(Load VIEW 246)
			(Load VIEW 57)
		)
		(if (and (== prevRoomNum 49) (ego has: iMagicHen))
			(door
				view: 602
				loop: 0
				cel: 6
				posn: 181 145
				setPri: 10
				ignoreActors:
				init:
				stopUpd:
			)
		else
			(door
				view: 602
				loop: 0
				cel: 0
				posn: 181 145
				setPri: 10
				init:
				stopUpd:
			)
		)
		(if (<= (ego y?) horizon)
			(ego y: (+ horizon (ego yStep?)))
		)
		(switch prevRoomNum
			(0 (ego posn: 128 166))
			(28
				(ego posn: 48 (+ horizon (ego yStep?) 1))
			)
			(3 (ego posn: 2 (ego y?)))
			(5 (ego posn: 317 (ego y?)))
			(10 (ego posn: (ego x?) 187))
			(49 (ego loop: 2 posn: 173 152))
		)
		(ego view: 2 init:)
		(ego setStep: 3 2)
		(if
		(and (== prevRoomNum 10) ogre (!= global179 TRUE))
			(= ogre (Actor new:))
			(ogre
				posn: 110 (+ 189 (- ogreY 75) 80)
				view: 250
				xStep: 6
				yStep: 2
				setCycle: Walk
				ignoreActors:
				init:
			)
			(curRoom setScript: ogreActions)
			(ogreTheme number: 5 loop: -1 play:)
		else
			(= ogre NULL)
		)
		(if (and (== gamePhase getTheHen) (not ogressIsHome))
			(= ogressIsHome TRUE)
			(= aOgress (Actor new:))
			(aOgress
				posn: 4 141
				view: 246
				xStep: 1
				yStep: 1
				setCycle: Walk
				ignoreActors:
				init:
			)
			(curRoom setScript: ogressActions)
			(aOgress setMotion: MoveTo 103 168 ogressActions)
			(ogressTheme number: 10 loop: -1 play:)
		)
		(if
			(and
				(not ogre)
				(< (Random 1 100) 35)
				(!= global179 TRUE)
				(!= prevRoomNum 49)
				(not (cast contains: aOgress))
			)
			(= ogre (Actor new:))
			(ogre
				posn: 1 169
				view: 250
				xStep: 6
				yStep: 2
				setCycle: Walk
				ignoreActors:
				init:
			)
			(if (< (Random 1 100) 50) (ogre posn: 110 239))
			(ogreTheme number: 5 loop: -1 play:)
			(curRoom setScript: ogreActions)
		)
		(if (== ogreState 4)
			(curRoom setScript: ogreActions)
			(ogreActions changeState: 200)
		)
		(if (== (door cel?) 0) (ego observeControl: 16384))
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (& (ego onControl: 0) $4000) (!= (door cel?) 0))
			(curRoom newRoom: 49)
		)
		(if (cast contains: ego)
			(if (ego inRect: 143 0 369 136)
				(ego setPri: 1)
			else
				(ego setPri: -1)
			)
		)
		(if (cast contains: ogre)
			(if (ogre inRect: 143 0 369 136)
				(ogre setPri: 1)
			else
				(ogre setPri: -1)
			)
		)
		(if (cast contains: aOgress)
			(cond 
				((aOgress inRect: 143 0 369 136) (aOgress setPri: 1))
				((< (ogressActions state?) 200) (aOgress setPri: -1))
			)
		)
		(if (== ogressState ogressAppears)
			(if
				(and
					(not
						(if (ego inRect: 134 0 369 137)
							(aOgress inRect: 104 149 369 185)
						)
					)
					(< (ego distanceTo: aOgress) 75)
				)
				(= ogressState ogressChases)
				(ogressActions changeState: 10)
				(Print 4 0)
			)
		)
		(if
			(and
				(== ogressState ogressAppears)
				(not (ego inRect: 134 0 369 137))
				(not (aOgress inRect: 104 149 369 185))
			)
			(= local4
				(GetAngle (aOgress x?) (aOgress y?) (ego x?) (ego y?))
			)
			(if
				(and
					(< (= newActHeading (aOgress heading?)) 15)
					(> local4 345)
				)
				(= newActHeading (+ newActHeading 360))
			)
			(if (< (Abs (- local4 newActHeading)) 15)
				(= ogressState ogressChases)
				(Print 4 0)
				(ogressActions changeState: 10)
			)
		)
	)
	
	(method (dispose)
		(= noWearCrown 0)
		(ego setPri: -1)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							((Said '/cottage') (Print 4 1))
							((Said '/door') (Print 4 2))
							((Said '/bucket') (Print 4 3))
							((Said '/buck') (if (cast contains: aOgress) (Print 4 4)))
							((Said '/window')
								(if (ego inRect: 266 140 304 155)
									(Print 4 5)
								else
									(Print 800 1)
								)
							)
							((Said '/giantess')
								(if (cast contains: aOgress)
									(switch (aOgress view?)
										(246 (Print 4 6))
										(245 (Print 4 7))
									)
								else
									(Print 4 8)
								)
							)
							((Said '[<around][/room]') (Print 4 9))
						)
					)
					(
					(and (cast contains: aOgress) (Said 'converse'))
						(if (and (== (aOgress view?) 246) (!= ogressState 3))
							(if (not (ego inRect: 143 0 319 136))
								(Print 4 10)
								(ogressActions changeState: 10)
								(= ogressState ogressChases)
							else
								(Print 4 11)
							)
						else
							(Print 4 12)
						)
					)
					((Said 'get,capture/buck')
						(cond 
							((cast contains: aOgress) (Print 4 13))
							((cast contains: aDeer) (Print 4 14))
							(else (Print 4 15))
						)
					)
					((Said 'get,capture/giantess') (Print 4 16))
					((Said 'open/door')
						(if (ego inRect: 163 144 199 155)
							(cond 
								(
								(or (cast contains: aOgress) (cast contains: ogre)) (Print 4 17))
								((!= gamePhase 2) (Print 4 18))
								(else
									(ego loop: 3)
									(HandsOff)
									(door setPri: 9 setCycle: EndLoop)
									(doorSound number: 300 play: door)
									(ego illegalBits: cWHITE)
								)
							)
						else
							(Print 800 1)
						)
					)
					((Said 'bang')
						(if (< (ego distanceTo: door) 10)
							(cond 
								((cast contains: ogre) (Print 4 19))
								((!= gamePhase getTheHen) (Print 4 20))
								((not (cast contains: aOgress))
									(Print 4 21)
									(curRoom setScript: ogressActions)
									(ogressActions changeState: 200)
								)
								(else (Print 4 22))
							)
						else
							(Print 800 1)
						)
					)
					((Said 'unlatch') (if (!= gamePhase getTheHen) (Print 4 18) else (Print 4 23)))
					((Said 'break/window') (Print 4 24))
					((Said 'break/door') (Print 4 25))
					((Said 'open/window') (Print 4 26))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(HandsOn)
		(if (cast contains: ego)
			(ego illegalBits: cWHITE)
			(ego setPri: -1)
		)
		(if (!= newRoomNumber 49) (= ogreState 0))
		(= noWearCrown 0)
		(super newRoom: newRoomNumber)
	)
)

(instance ogressActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (if aOgress (= seconds 3)))
			(1
				(= ogressState ogressAppears)
				(aOgress setLoop: 0)
			)
			(2
				(aOgress setMotion: MoveTo 183 155 ogressActions)
			)
			(3 (door setCycle: EndLoop self))
			(4
				(= ogressState 3)
				(aOgress
					baseSetter: (ScriptID 0 1)
					setPri: 9
					setMotion: MoveTo 187 144 self
				)
			)
			(5 (door setCycle: BegLoop self))
			(6
				(= ogressState 0)
				(ogressTheme client: 0 stop:)
				(aOgress dispose:)
				(= aOgress 0)
			)
			(10
				(= aDeer (Prop new:))
				(aDeer
					view: 530
					ignoreActors:
					setPri: (+ (aOgress priority?) 1)
					posn: (- (aOgress x?) 6) (+ (aOgress y?) 3)
					setCycle: EndLoop self
					init:
				)
				(aOgress setLoop: -1 setMotion: 0 view: 245)
				(door setCycle: 0)
			)
			(11
				(aOgress
					setAvoider: (Avoider new:)
					xStep: 6
					yStep: 2
					setLoop: -1
					setCycle: Walk
					ignoreActors:
					setMotion: Chase ego 5 self
				)
				(if (cast contains: aDeer)
					(aDeer ignoreActors: 0 setPri: -1)
				)
			)
			(12
				(ogressTheme client: 0 stop:)
				(ogressTheme number: 11 loop: 1 play: self)
				(HandsOff)
				(ego dispose:)
				(aOgress view: 57 setCycle: Forward)
				(Print 4 27 #at -1 10 #draw)
			)
			(13 (= seconds 2))
			(14 (= dead TRUE))
			(200
				(door setCycle: EndLoop self)
				(= aOgress (Actor new:))
				(aOgress
					view: 245
					setPri: 9
					loop: 2
					ignoreActors:
					baseSetter: (ScriptID 0 1)
					posn: 186 145
					init:
				)
				(HandsOff)
			)
			(201 (= seconds 1))
			(202 (self changeState: 11))
		)
	)
)

(instance ogreActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (cast contains: ogre) (ogreActions changeState: 1))
			)
			(1
				(= local2 1)
				(if (ego inRect: 223 74 319 136)
					(ogre setMotion: MoveTo 97 123 self)
				else
					(self changeState: 2)
				)
			)
			(2
				(ogre
					setAvoider: (Avoider new:)
					xStep: 6
					yStep: 2
					ignoreActors:
					setMotion: Chase ego 5 self
				)
			)
			(3
				(HandsOff)
				(ego dispose:)
				(ogre view: 78 setCycle: EndLoop self)
			)
			(4
				(ogreTheme client: 0 stop:)
				(ogreTheme number: 6 loop: 1 play:)
				(ogre setAvoider: 0 view: 79 setCycle: Walk self)
				(Print 4 28 #at -1 10 #draw)
				(cond 
					((ogre inRect: -50 70 350 93) (ogre setMotion: MoveTo 37 87 self))
					(
						(or
							(ogre inRect: -50 93 350 136)
							(ogre inRect: 92 135 141 148)
						)
						(self changeState: 5)
						(= local0 (Timer setReal: killOgre 15))
					)
					(else
						(self changeState: 7)
						(= local0 (Timer setReal: killOgre 15))
					)
				)
			)
			(5
				(ogre setMotion: MoveTo 75 120 self)
			)
			(6
				(ogre setMotion: MoveTo 75 164 self)
			)
			(7
				(ogre setMotion: MoveTo 182 164 self)
			)
			(8
				(if (timers contains: local0) (local0 dispose: delete:))
				(if (== (door cel?) 0)
					(door setCycle: EndLoop self)
				else
					(self changeState: 9)
				)
			)
			(9
				(door ignoreActors:)
				(ogre setMotion: MoveTo 182 154 self)
			)
			(10 (= seconds 3))
			(11
				(sounds eachElementDo: #stop 0)
				(= seconds 2)
			)
			(12 (= dead TRUE))
			(200 (= seconds 5))
			(201
				(= ogre (Actor new:))
				(ogre
					loop: 2
					posn: 186 147
					view: 250
					baseSetter: (ScriptID 0 1)
					setCycle: Walk
					setAvoider: Avoider
					init:
				)
				(ogreTheme number: 5 loop: -1 play:)
				(if (< (ogre distanceTo: ego) 10)
					(self changeState: 1)
				else
					(ogre setMotion: MoveTo 181 159 self)
				)
			)
			(202
				(ogre observeControl: 16384)
				(self changeState: 1)
			)
		)
	)
)

(instance killOgre of Script
	(properties)
	
	(method (cue)
		(= dead TRUE)
	)
)
