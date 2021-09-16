;;; Sierra Script 1.0 - (do not remove this comment)
(script# HAUNTED_HOUSE)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	regHauntedHouse 0
)
(synonyms
	(room cottage)
)

(local
	ghostLoop
	seenGhostBoy
)
(class GhostWander of Motion
	(properties
		ux 0
		uy 0
		lx 0
		ly 0
	)
	
	(method (init param1 theUx theUy theLx theLy &tmp theUx_2 theUy_2)
		(= ux theUx)
		(= uy theUy)
		(= lx theLx)
		(= ly theLy)
		(switch (/ (Random 10 29) 10)
			(1 (= theUx_2 ux))
			(2 (= theUx_2 lx))
		)
		(switch (/ (Random 10 29) 10)
			(1 (= theUy_2 uy))
			(2 (= theUy_2 ly))
		)
		(super init: param1 theUx_2 theUy_2)
		(super doit:)
	)
	
	(method (doit)
		(super doit:)
		(if (client isStopped:) (self moveDone:))
	)
	
	(method (moveDone)
		(self init: client ux uy lx ly)
	)
)

(instance theGhost of Actor)

(instance regHauntedHouse of Region
	(method (init)
		(if initialized (return))
		(= noWearCrown (= keep TRUE))
		(super init:)
		(if isNightTime
			(theGhost
				view: 201
				illegalBits: 0
				ignoreActors: TRUE
				posn: 999 999
				init:
			)
			(if (not mansionPhase)
				(= mansionPhase mansionBABY)
			)
			(self notify: mansionPhase)
		)
		(miserGhostMusic owner: self init:)
		(ladyGhostMusic owner: self init:)
		(lordGhostMusic owner: self init:)
		(boyGhostMusic owner: self init:)
	)
	
	(method (dispose)
		(if (== keep FALSE)
			(= noWearCrown FALSE)
			(super dispose:)
		)
	)
	
	(method (newRoom n)
		(sounds eachElementDo: #stop 0)
		(if (IsObject script)
			(script cue: -2 n)
		)
		(= ghostWandering FALSE)
		(if (== n 17)
			(HandsOn)
		)
		(super newRoom: n)
	)
	
	(method (notify nextPhase)
		(cond 
			((and script (not argc))
				(script cue:)
			)
			((and script (< nextPhase 0))
				(script cue: nextPhase &rest)
			)
			(else
				(self
					setScript:
						(switch (= mansionPhase nextPhase)
							(mansionBABY babyScript)
							(mansionMISER miserScript)
							(mansionLADY ladyScript)
							(mansionLORD lordScript)
							(mansionBOY boyScript)
							(else  NULL)
						)
				)
			)
		)
	)
)

(instance babyScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ghostRoomNum 59)
				(= seconds 5)
			)
			(1
				(Print 603 0)
				(= seconds 10)
			)
			(2
				(if
					(and
						(!= curRoomNum 61)
						(!= curRoomNum 66)
						(!= curRoomNum 59)
						(!= curRoomNum 58)
					)
					(Print 603 1)
				)
				(-- state)
				(= seconds (Random 10 30))
			)
		)
	)
	
	(method (cue)
		(if (not argc)
			(super cue:)
		)
	)
)

(instance miserScript of Script
	(method (changeState newState &tmp ghostX ghostY toX)
		(switch (= state newState)
			(0
				(Load VIEW 200)
				(Load VIEW 201)
				(= ghostRoomNum 164)
				(= ghostLoop 1)
				(= seconds (Random 5 10))
			)
			(1
				(Print 603 2)
				(= cycles 5)
			)
			(2
				(= seconds 3)
			)
			(3
				(if
					(and
						(!= curRoomNum 66)
						(!= curRoomNum 61)
						(!= curRoomNum 58)
					)
					(Print 603 3)
				)
				(-- state)
				(= seconds (Random 40 99))
			)
			(4
				(= seconds 0)
				(= ghostWandering TRUE)
				(miserGhostMusic play:)
				(switch ghostRoomNum
					(67 (= ghostY 145))
					(68 (= ghostY 180))
					(64 (= ghostY 145))
				)
				(switch ghostLoop
					(0 (= ghostX -33))
					(1 (= ghostX 333))
				)
				(switch ghostLoop
					(0 (= toX 333))
					(1 (= toX -33))
				)
				(theGhost
					view: 201
					illegalBits: 0
					posn: ghostX ghostY
					ignoreActors: 1
					init:
					setLoop: ghostLoop
					setCycle: Walk
					setMotion: MoveTo toX ghostY self
				)
				(= ghostHaunts TRUE)
			)
			(5
				(= ghostHaunts 0)
			)
			(6
				(HandsOff)
				(Print 603 4)
				(ego put: iGoldCoins -1)
				(theGame changeScore: 2)
				(theGhost setLoop: setMotion: 0)
				(= seconds 2)
			)
			(7
				(theGhost
					view: 200
					cycleSpeed: 1
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(8
				(= ghostHaunts FALSE)
				(HandsOn)
				(regHauntedHouse notify: 3)
			)
		)
	)
	
	(method (cue param1)
		(cond 
			((not argc) (super cue:))
			((== param1 -1)
				(= seconds 0)
				(self
					changeState: (cond 
						((< state 4) 4)
						((< state 6) 6)
					)
				)
			)
			((== param1 -2)
				(switch ghostRoomNum
					(164
						(= ghostRoomNum 64)
						(= ghostLoop 1)
					)
					(64
						(if (== ghostLoop 1)
							(= ghostRoomNum 68)
						else
							(= ghostRoomNum 164)
						)
					)
					(68
						(if (== ghostLoop 1)
							(= ghostRoomNum 67)
						else
							(= ghostRoomNum 64)
						)
					)
					(67
						(if (== ghostLoop 1)
							(= ghostRoomNum 167)
						else
							(= ghostRoomNum 68)
						)
					)
					(167
						(= ghostRoomNum 67)
						(= ghostLoop 0)
					)
				)
				(if (< state 6)
					(self changeState: 2)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp i)
		(if
			(or
				(event claimed?)
				(!= (event type?) saidEvent)
				(!= curRoomNum ghostRoomNum)
				(not (cast contains: theGhost))
				(== ghostHaunts FALSE)
			)
			(return)
		)
		(cond 
			((Said 'look/chain')
				(Print 603 5)
			)
			((Said 'get/chain')
				(Print 603 6)
			)
			((Said 'deliver>')
				(cond 
					(
						(or
							(not (= i (inventory saidMe:)))
							(not (ego has: (inventory indexOf: i)))
						)
						(event claimed: FALSE)
					)
					((> (ego distanceTo: theGhost) 30)
						(NotClose)
					)
					((!= (inventory indexOf: i) iGoldCoins)
						(Print 603 7)
					)
					(else
						(self cue: -1)
					)
				)
			)
			((Said 'converse')
				(Print 603 8)
			)
			((Said 'look')
				(Print 603 9)
			)
			((Said 'kiss')
				(Print 603 10)
			)
			((Said 'capture,get')
				(Print 603 11)
			)
			((Said 'kill')
				(Print 603 12)
			)
			((Said 'help')
				(Print 603 13)
			)
		)
	)
)

(instance ladyScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGhost posn: (theGhost x?) (+ (theGhost y?) 1000))
				(Load VIEW 202)
				(Load VIEW 203)
				(= ghostRoomNum 60)
				(= ghostHaunts FALSE)
				(= seconds 3)
			)
			(1
				(Print 603 14)
				(= seconds (Random 10 30))
			)
			(2
				(= seconds 3)
			)
			(3
				(if
					(and
						(!= curRoomNum 66)
						(!= curRoomNum 61)
						(!= curRoomNum 58)
						(!= curRoomNum 60)
					)
					(Print 603 15)
				)
				(-- state)
				(= seconds (Random 50 99))
			)
			(4
				(= ghostWandering TRUE)
				(= seconds 3)
			)
			(5
				(ladyGhostMusic play:)
				(= ghostHaunts TRUE)
				(theGhost
					view: 202
					posn: 85 133
					ignoreActors: TRUE
					cycleSpeed: 3
					loop: 0
					cel: 0
					setCycle: EndLoop self
					init:
				)
			)
			(6
				(theGhost view: 203 setCycle: Forward)
			)
			(7
				(HandsOff)
				(theGhost
					view: 202
					loop: 0
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(8
				(= ghostHaunts FALSE)
				(HandsOn)
				(= ghostRoomNum 67)
				(regHauntedHouse notify: 4)
			)
		)
	)
	
	(method (cue param1)
		(cond 
			((not argc) (super cue:))
			((== param1 -1)
				(= seconds 0)
				(self
					changeState: (cond 
						((< state 4) 4)
						((< state 7) 7)
					)
				)
			)
			((and (== param1 -2) (< state 7))
				(self changeState: 2)
			)
		)
	)
)

(instance lordScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGhost posn: (theGhost x?) (+ (theGhost y?) 1000))
				(Load VIEW 204)
				(Load VIEW 206)
				(= seconds 3)
			)
			(1
				(Print 603 16)
				(= seconds (Random 3 5))
			)
			(2
				(= seconds 3)
			)
			(3
				(if
					(and
						(!= curRoomNum 66)
						(!= curRoomNum 61)
						(!= curRoomNum 58)
					)
					(Print 603 17)
				)
				(-- state)
				(= seconds (Random 40 99))
			)
			(4
				(= ghostWandering TRUE)
				(= seconds (Random 2 5))
			)
			(5
				(= seconds 0)
				(lordGhostMusic play:)
				(theGhost
					view: 204
					cel: 0
					posn: 150 160
					illegalBits: 0
					cycleSpeed: 1
					ignoreActors: TRUE
					setStep: 4 2
					setCycle: EndLoop self
					init:
				)
			)
			(6
				(theGhost
					view: 206
					loop: (Random 0 3)
					moveSpeed: 1
					cycleSpeed: 1
					setLoop: -1
					setCycle: Walk
				)
				(if (== curRoomNum 68)
					(theGhost setMotion: GhostWander 78 156 225 185)
				else
					(theGhost setMotion: GhostWander 75 130 225 175)
				)
				(= ghostHaunts TRUE)
				(= seconds (Random 13 30))
			)
			(7
				(theGhost
					view: 204
					loop: 0
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(8
				(= ghostHaunts FALSE)
				(= state 4)
				(= seconds (Random 20 99))
			)
			(9
				(HandsOff)
				(Print 603 18)
				(theGhost setMotion: 0)
				(= seconds 3)
			)
			(10
				(= seconds 0)
				(ego put: iMedal -1)
				(theGame changeScore: 2)
				(theGhost
					view: 204
					loop: 0
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(11
				(HandsOn)
				(= ghostHaunts FALSE)
				(regHauntedHouse notify: 5)
			)
		)
	)
	
	(method (cue param1 param2)
		(cond 
			((not argc)
				(super cue:)
			)
			((== param1 -1)
				(= seconds 0)
				(self changeState:
					(cond 
						((< state 4) 4)
						((< state 9) 9)
					)
				)
			)
			((== param1 -2)
				(= ghostRoomNum
					(if
						(and
							(<= 64 param2)
							(<= param2 68)
							(!= param2 66)
							(Random 0 1)
						)
						param2
					else
						0
					)
				)
				(self changeState: 2)
			)
		)
	)
	
	(method (handleEvent event &tmp i)
		(if
			(or
				(event claimed?)
				(!= (event type?) saidEvent)
				(!= curRoomNum ghostRoomNum)
				(not ghostWandering)
				(== ghostHaunts FALSE)
			)
			(return)
		)
		(cond 
			((Said 'deliver>')
				(cond 
					(
						(or
							(not (= i (inventory saidMe:)))
							(not (ego has: (inventory indexOf: i)))
						)
						(event claimed: FALSE)
					)
					((> (ego distanceTo: theGhost) 30)
						(NotClose)
					)
					((!= (inventory indexOf: i) iMedal)
						(Print 603 19)
					)
					(else
						(self changeState: 9)
					)
				)
			)
			((Said 'converse')
				(Print 603 20)
			)
			((Said 'look')
				(Print 603 21)
			)
			((Said 'kiss')
				(Print 603 10)
			)
			((Said 'get,capture/anyword')
				(Print 603 22)
			)
			((Said 'kill/anyword')
				(Print 603 12)
			)
			((Said 'help/anyword')
				(Print 603 23)
			)
		)
	)
)

(instance boyScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 2)
			)
			(1
				(theGhost posn: (theGhost x?) (+ (theGhost y?) 1000))
				(Load VIEW 207)
				(Load VIEW 208)
				(= mansionPhase mansionBOY)
				(= seconds 2)
			)
			(2
				(boyGhostMusic client: 0 stop:)
				(cond 
					((== curRoomNum 60)
						(= ghostWandering TRUE)
						(= cycles 1)
					)
					(
						(and
							(!= curRoomNum 66)
							(!= curRoomNum 61)
							(!= curRoomNum 58)
						)
						(= ghostWandering TRUE)
						(= seconds (Random 1 3))
					)
				)
			)
			(3
				(boyGhostMusic play:)
				(theGhost
					view: 207
					illegalBits: 0
					ignoreActors: TRUE
					setPri: -1
					loop: 0
					cel: 0
					setCycle: EndLoop self
					posn:
						(switch curRoomNum
							(60 159)
							(64 83)
							(65 255)
							(67 239)
							(68 165)
						)
						(switch curRoomNum
							(60 136)
							(64 124)
							(65 148)
							(67 136)
							(68 83)
						)
					init:
				)
				(if (== curRoomNum 60)
					(theGhost setPri: 10)
				)
			)
			(4
				(if (== seenGhostBoy FALSE)
					(= seenGhostBoy TRUE)
					(Print 603 24)
				else
					(Print 603 25)
				)
				(theGhost
					view: 208
					moveSpeed: 0
					cycleSpeed: 0
					setLoop: -1
					setCycle: Walk
				)
				(switch curRoomNum
					(60
						(theGhost
							setPri: 10
							setCycle: Forward
							setMotion: MoveTo 158 15
						)
						(= ghostRoomNum 63)
					)
					(64
						(theGhost setPri: 9 setMotion: MoveTo 0 143 self)
						(= ghostRoomNum 68)
					)
					(65
						(theGhost setMotion: MoveTo 240 190 self)
						(= ghostRoomNum 64)
					)
					(67
						(theGhost setMotion: MoveTo 300 136 self)
						(= ghostRoomNum 68)
					)
					(68
						(theGhost setMotion: MoveTo 193 73 self)
						(= ghostRoomNum 60)
					)
				)
			)
			(5
				(theGhost
					view: 207
					loop: 0
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(6
				(= ghostWandering FALSE)
				(theGhost posn: (theGhost x?) (+ (theGhost y?) 1000))
			)
			(11
				(= ghostWandering TRUE)
				(Load VIEW 209)
				(boyGhostMusic play:)
				(theGhost
					view: 207
					loop: 1
					cel: 0
					posn: 167 117
					setPri: 8
					cycleSpeed: 1
					setCycle: EndLoop self
					illegalBits: 0
					ignoreActors:
					init:
				)
			)
			(12
				(theGhost
					view: 209
					loop: (Random 0 3)
					cycleSpeed: 1
					setCycle: Forward
				)
				(= seconds 5)
			)
			(13
				(if (== curRoomNum 63)
					(Print 603 26)
				)
				(= seconds 3)
			)
			(14
				(theGhost loop: (Random 0 3))
				(-- state)
				(= cycles (Random 30 60))
			)
			(21
				(HandsOff)
				(Print 603 27)
				(ego put: iToyHorse -1)
				(theGame changeScore: 2)
				(= seconds 2)
			)
			(22
				(= mansionPhase mansionFINAL)
				(theGhost
					view: 207
					loop: 1
					setCel: 255
					setCycle: BegLoop self
					init:
				)
			)
			(23
				(HandsOn)
				(theGhost dispose:)
				(regHauntedHouse notify: mansionFINAL)
			)
		)
	)
	
	(method (cue param1)
		(cond 
			((not argc)
				(super cue:)
			)
			((== param1 -1)
				(= seconds 0)
				(self changeState:
					(cond 
						((== curRoomNum 63) 11)
						((< state 11) 2)
					)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp i)
		(if
			(or
				(event claimed?)
				(!= (event type?) saidEvent)
				(!= curRoomNum ghostRoomNum)
				(not ghostWandering)
			)
			(return)
		)
		(cond 
			((Said 'deliver>')
				(cond 
					(
						(or
							(not (= i (inventory saidMe:)))
							(not (ego has: (inventory indexOf: i)))
						)
						(event claimed: FALSE)
					)
					((> (ego distanceTo: theGhost) 30)
						(NotClose)
					)
					((!= (inventory indexOf: i) iToyHorse)
						(Print 603 28)
					)
					((!= curRoomNum 63)
						(Print 603 29)
					)
					(else
						(self changeState: 21)
					)
				)
			)
			((Said 'converse')
				(Print 603 30)
			)
			((Said 'kiss') (Print 603 10))
			((Said 'look/ghost[<boy]')
				(if (== curRoomNum 63)
					(Print 603 31)
				else
					(Print 603 32)
				)
			)
			((Said 'play/ghost[<boy]')
				(Print 603 33)
			)
			((Said 'capture,get/ghost[<boy]')
				(Print 603 11)
			)
			((Said 'kill/anyword')
				(Print 603 12)
			)
			((Said 'help,save/anyword')	;EO: Fixed said spec
				(Print 603 34)
			)
		)
	)
)

(instance lordGhostMusic of Sound
	(properties
		number 25
	)
)

(instance miserGhostMusic of Sound
	(properties
		number 23
	)
)

(instance ladyGhostMusic of Sound
	(properties
		number 24
	)
)

(instance boyGhostMusic of Sound
	(properties
		number 26
	)
)
