;;; Sierra Script 1.0 - (do not remove this comment)
(script# 67)
(include game.sh) (include "67.shm")
(use Main)
(use CastOpen)
(use Target)
(use Procs)
(use Talker)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm67 0
	foxTalker 1
)

(local
	eyeXY = [
		219 313
		90 246
		53 60
		52 148
		]
	eyeIndex
	eyeIndex2
	foxInRoom
	openSpellSuccess
	openSpellFail
	foxGone
	talkedToFox
	local15
	local16
)
(instance rm67 of Room
	(properties
		picture 67
		style DISSOLVE
		horizon 55
	)
	
	(method (init)
		(self setRegions: FOREST)
		(LoadMany RES_VIEW 67 510)
		(super init:)
		(StatusLine enable:)
		(if (or (== enter67 6) (== enter67 9))
			(sinclair init:)
		)
		(if (== enter67 2)
			(= foxInRoom TRUE)
			(LoadMany RES_VIEW 1068 68 522 518)
			(fox init: setScript: foxCallForHelp)
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							123 93
							123 75
							160 75
							160 93
						yourself:
					)
			)
			(messager say: N_ROOM NULL NULL 1)
		)
		(if (not foxInRoom)
			(eyes1
				setPri: 15
				init:
				stopUpd:
				setScript: peekABooScript1
			)
			(eyes2
				setPri: 15
				init:
				stopUpd:
				setScript: peekABooScript2
			)
		)
	)
	
	(method (doit)
		(if
			(and
				foxInRoom
				(not talkedToFox)
				(not (fox script?))
				(not (ego script?))
				(< (ego distanceTo: fox) 75)
			)
			(= talkedToFox TRUE)
			(fox setScript: foxExplains)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn67)
		(= egoY (ego y?))
		(if (curRoom obstacles?)
			((curRoom obstacles?) dispose:)
			(curRoom obstacles: 0)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_OPEN
				(if foxInRoom
					(if (TrySkill OPEN 25)
						(= openSpellSuccess TRUE)
						(SolvePuzzle f67SaveFox 10)
					else
						(= openSpellFail TRUE)
					)
					(ego setScript: foxFreed)
				else
					(messager say: N_ROOM V_OPEN C_CUE)
				)
			)
			(V_DETECT
				(if foxInRoom
					(messager say: N_ROOM V_DETECT NULL)
				else
					(messager say: N_ROOM V_DETECT C_CUE)
				)
			)
			(V_DAZZLE
				(if foxInRoom
					(messager say: N_ROOM V_DAZZLE NULL)
				else
					(messager say: N_ROOM V_OPEN C_CUE)
				)
			)
			(V_CALM
				(if foxInRoom
					(messager say: N_ROOM V_CALM NULL)
				else
					(messager say: N_ROOM V_OPEN C_CUE)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance fox of TargActor
	(properties
		x 137
		y 90
		noun N_FOX
		view 67
		targDeltaY 10
	)
	
	(method (init)
		(HandsOff)
		(if (== prevRoomNum 57)
			(ego setScript: foxNorth)
		else
			(ego setScript: foxWalk)
		)
		(super init:)
	)
	
	(method (dispose)
		(if (curRoom obstacles?)
			((curRoom obstacles?) dispose:)
			(curRoom obstacles: 0)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 74
						185 79
						152 69
						144 23
						130 23
						115 68
						78 82
						0 82
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 120
						89 161
						177 161
						319 117
						319 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						120 94
						120 80
						147 80
						147 94
					yourself:
				)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(if (not talkedToFox)
					(messager say: N_FOX V_ALTTALK NULL)
					(= talkedToFox TRUE)
				else
					(messager say: N_FOX V_ALTTALK C_CUE)
				)
			)
			(V_DO
				(if foxInRoom
					(if
						(and
							(< (ego y?) 112)
							(< 60 (ego x?))
							(< (ego x?) 112)
						)
						(messager say: N_FOX V_DO NULL)
					else
						(SolvePuzzle f67SaveFox 10)
						(ego setScript: foxFreed)
					)
				else
					(messager say: N_FOX V_DO C_CUE)
				)
			)
			(70
				(if foxInRoom
					(if local15
						(dart init: setScript: flameDartFox)
					else
						(messager say: N_FOX 70 0)
						(= local15 1)
					)
				else
					(messager say: N_ROOM 51 1)
				)
			)
			(V_ROCK
				(self setScript: foxDies)
			)
			(V_DAGGER
				(self setScript: foxDies)
			)
			(V_SWORD
				(self setScript: foxDies)
			)
			(V_LOCKPICK
				(if foxInRoom
					(if
						(and
							(< (ego y?) 112)
							(< 60 (ego x?))
							(< (ego x?) 112)
						)
						(messager say: N_FOX V_DO NULL)
					else
						(TrySkill PICK 30)
						(SolvePuzzle f67SaveFox 10)
						(ego setScript: foxFreed)
					)
				else
					(messager say: N_FOX V_DO C_CUE)
				)
			)
			(V_THIEFKIT
				(if foxInRoom
					(if
						(and
							(< (ego y?) 112)
							(< 60 (ego x?))
							(< (ego x?) 112)
						)
						(messager say: N_FOX V_DO NULL)
					else
						(TrySkill PICK 30)
						(SolvePuzzle f67SaveFox 10)
						(ego setScript: foxFreed)
					)
				else
					(messager say: N_FOX V_DO C_CUE)
				)
			)
			(V_BRASSKEY
				(if foxInRoom
					(if
						(and
							(< (ego y?) 112)
							(< 60 (ego x?))
							(< (ego x?) 112)
						)
						(messager say: N_FOX V_DO NULL)
					else
						(TrySkill PICK 30)
						(SolvePuzzle f67SaveFox 10)
						(ego setScript: foxFreed)
					)
				else
					(messager say: N_FOX V_DO C_CUE)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (getHurt)
		(self setScript: distantFoxDies)
	)
)

(instance dart of Actor
	(properties
		x 1000
		y 500
		z 12
		view 522
		illegalBits $0000
	)
)

(instance sinclair of Actor
	(properties
		x 350
		y 109
		noun N_SINCLAIR
		view 67
		loop 8
		signal (| ignrAct fixedLoop)
		illegalBits $0000
	)
	
	(method (init)
		(HandsOff)
		(ego setScript: dinoWalk)
		(super init:)
	)
	
	(method (doit)
		(cond 
			((and (not local16) (== (sinclair cel?) 0))
				(= local16 1)
				(ShakeScreen 1)
			)
			((and local16 (== (sinclair cel?) 1))
				(= local16 0)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (== enter67 6)
					(messager say: N_SINCLAIR V_LOOK NULL)
				else
					(messager say: N_SINCLAIR V_LOOK C_CUE)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance trap of View
	(properties
		x 137
		y 90
		noun N_TRAP
		view 67
		loop 1
		cel 14
		signal (| ignrAct fixPriOn)
	)
)

(instance eyes1 of Prop
	(properties
		view 67
		loop 6
		priority pWHITE
		signal fixPriOn
		cycleSpeed 3
	)
)

(instance eyes2 of Prop
	(properties
		view 67
		loop 7
		priority pWHITE
		signal fixPriOn
		cycleSpeed 3
	)
)

(instance foxCallForHelp of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fox setCycle: EndLoop)
			)
			(1
				(messager say: N_ROOM NULL C_FOXBEGS1 1 self)
				(fox loop: 0 cel: 0 setCycle: Forward)
			)
			(2
				(fox setCycle: EndLoop)
				(= ticks 480)
			)
			(3
				(fox loop: 0 cel: 0 setCycle: Forward)
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(messager say: N_ROOM NULL C_FOXBEGS2 1 self)
			)
			(4
				(fox setCycle: EndLoop self)
			)
			(5
				(client setScript: thrashAndWait)
			)
		)
	)
)

(instance foxWillNotFight of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(messager say: N_ROOM NULL C_FOXBEGS3)
			)
			(1
				(fox loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(2
				(messager say: N_ROOM NULL C_FOXWONTFIGHT)
			)
		)
	)
)

(instance foxExplains of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(messager say: N_ROOM NULL C_FOXEXPLAINS 0 self)
			)
			(1
				(fox loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(2
				(HandsOn)
				(fox setScript: thrashAndWait)
			)
		)
	)
)

(instance distantFoxDies of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= foxGone TRUE)
				(= foxInRoom FALSE)
				(fox loop: 2 cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(1
				(SolvePuzzle f67KillFox -10)
				(= hitDaggers 0)
				(HandsOn)
				(messager say: N_ROOM NULL C_KILLFOX)
				(client dispose:)
			)
		)
	)
)

(instance foxDies of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= foxGone TRUE)
				(= foxInRoom FALSE)
				(if (ego inRect: 41 109 111 138)
					(self cue:)
				else
					(ego
						ignoreActors:
						illegalBits: 0
						setMotion: MoveTo 120 130 self
					)
				)
			)
			(1
				(ego
					ignoreActors:
					setMotion: MoveTo (+ (fox x?) 20) (+ (fox y?) 2) self
				)
			)
			(2
				(ego view: 518 setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(3
				(ego setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(4
				(ego setCycle: BegLoop self)
			)
			(5
				(NormalEgo)
				(fox loop: 2 cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(6
				(SolvePuzzle f67KillFox -10)
				(HandsOn)
				(= hitDaggers 0)
				(messager say: N_ROOM NULL C_KILLFOX)
				(client dispose:)
			)
		)
	)
)

(instance foxFreed of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (or openSpellSuccess openSpellFail)
					(CastOpen rm67 self)
				else
					(self cue:)
				)
			)
			(1
				(if (not (== (fox script?) thrashAndWait))
					(fox setScript: thrashAndWait)
				)
				(if (IsObject foxCallForHelp)
					(foxCallForHelp dispose:)
				)
				(ego ignoreActors: illegalBits: 0)
				(if (ego inRect: 41 109 111 138)
					(self cue:)
				else
					(ego setMotion: PolyPath 112 130 self)
				)
			)
			(2
				(Face ego fox)
				(= ticks 60)
			)
			(3
				(ego setMotion: PolyPath (- (fox x?) 20) (fox y?) self)
			)
			(4
				(ego view: 510 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(5
				(cond 
					(openSpellFail
						(messager say: N_ROOM NULL C_FREEFOX 1 self)
					)
					(openSpellSuccess
						(messager say: N_ROOM NULL C_FREEFOX 2 self)
					)
					(else
						(messager say: N_ROOM NULL C_FREEFOX 3 self)
					)
				)
			)
			(6
				(ego x: (- (ego x?) 5) setCycle: BegLoop self)
			)
			(7
				(if openSpellFail
					(= openSpellFail FALSE)
					(NormalEgo)
					(HandsOn)
					(fox setScript: foxExplains)
					(client setScript: 0)
				else
					(= foxGone TRUE)
					(= foxInRoom FALSE)
					(= ticks 60)
				)
			)
			(8
				(fox setLoop: 3 setCel: 0 setCycle: CycleTo 6 1 self)
			)
			(9
				(trap addToPic:)
				(fox setCycle: EndLoop self)
			)
			(10
				(fox
					setLoop: 4
					setCel: 0
					setMotion: MoveTo (+ (fox x?) 18) (+ (fox y?) 6)
					setCycle: CycleTo 3 1 self
				)
			)
			(11
				(fox setLoop: 3 setCel: 8)
				(= foxInRoom FALSE)
				(messager say: N_ROOM NULL C_FREEFOX 4 self)
			)
			(12
				(messager say: N_ROOM NULL C_FREEFOX 5 self)
			)
			(13
				(ego view: 68 loop: 0 cel: 0 setCycle: EndLoop)
				(fox
					setLoop: 4
					cycleSpeed: 3
					setStep: 6 6
					moveSpeed: 3
					setCycle: Forward
					setMotion: MoveTo 335 120 self
				)
			)
			(14
				(NormalEgo)
				(HandsOn)
				(fox dispose:)
				(client setScript: 0)
				(self dispose:)
			)
		)
	)
)

(instance flameDartFox of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= foxGone TRUE)
				(= foxInRoom FALSE)
				(fox setScript: 0)
				(ego
					view: 522
					setLoop: (if (< (ego x?) (fox x?)) 0 else 1)
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(1
				(ego setCycle: EndLoop)
				(dart
					setLoop: 2
					setStep: 18 12
					setPri: 12
					ignoreActors: TRUE
					posn: (ego x?) (ego y?)
					show:
					setCycle: Forward
					startUpd:
				)
				(dart setMotion: MoveTo (+ (fox x?) 3) (fox y?) self)
			)
			(2
				(dart setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(3
				(fox loop: 2 cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(4
				(HandsOn)
				(NormalEgo)
				(messager say: N_ROOM NULL C_KILLFOX)
				(= ticks 120)
			)
		)
	)
)

(instance getTrap of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: illegalBits: 0)
				(if (ego inRect: 41 109 111 138)
					(self cue:)
				else
					(ego setMotion: MoveTo 112 130 self)
				)
			)
			(1
				(ego setMotion: MoveTo 58 113 self)
			)
			(2
				(ego view: 510 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(3
				(messager say: N_ROOM NULL C_CANTGETTRAP)
				(self cue:)
			)
			(4
				(= cycles 8)
			)
			(5
				(ego setCycle: BegLoop self)
			)
			(6
				(NormalEgo)
				(HandsOn)
			)
		)
	)
)

(instance thrashAndWait of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fox setLoop: 0 setCycle: EndLoop self)
			)
			(1
				(fox cel: 0)
				(= ticks (Random 180 480))
			)
			(2
				(if (and foxInRoom (not (== (ego script?) foxFreed)))
					(self changeState: 0)
				else
					(self cue:)
				)
			)
		)
	)
)

(instance peekABooScript1 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= eyeIndex (Random 1 4))
				(client cel: 0)
				(self cue:)
			)
			(1
				(client
					x: [eyeXY (- eyeIndex 1)]
					y: [eyeXY (+ eyeIndex 3)]
					show:
					setCycle: EndLoop self
				)
			)
			(2
				(= ticks (Random 300 600))
			)
			(3
				(self changeState: 0)
			)
		)
	)
)

(instance peekABooScript2 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= eyeIndex2 (Random 1 4))
				(client cel: 0)
				(self cue:)
			)
			(1
				(client
					x: [eyeXY (- eyeIndex2 1)]
					y: [eyeXY (+ eyeIndex2 3)]
					show:
					setCycle: EndLoop self
				)
			)
			(2
				(= ticks (Random 300 600))
			)
			(3
				(self changeState: 0)
			)
		)
	)
)

(instance dinoWalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 90)
			)
			(1
				(HandsOff)
				(ShakeScreen 1)
				(if (== enter67 6)
					(ego setMotion: PolyPath 215 145 self)
				else
					(ego setMotion: PolyPath 100 155 self)
				)
			)
			(2
				(HandsOff)
				(theIconBar enable: ICON_LOOK)
				(ChangeTheCursor 2)
				(user canInput: TRUE)
				(ego loop: 3)
				(if (== enter67 6)
					(sinclair setCycle: Walk setMotion: MoveTo -40 109 self)
				else
					(sinclair
						posn: -40 109
						setStep: 4 2
						setLoop: 9
						setCycle: Walk
						setMotion: MoveTo 350 109 self
					)
				)
			)
			(3
				(ego setMotion: MoveTo 160 140 self)
			)
			(4
				(ego loop: 2)
				(HandsOn)
				(NormalEgo)
				(sinclair dispose:)
				(self dispose:)
			)
		)
	)
)

(instance foxWalk of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 150 130 self)
			)
			(1
				(HandsOn)
				(NormalEgo loopN)
				(= ticks 30)
			)
			(2
				(foxCallForHelp cue:)
				(ego setScript: 0)
				(self dispose:)
			)
		)
	)
)

(instance foxNorth of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 102 91 self)
			)
			(1
				(ego setMotion: MoveTo 102 121 self)
			)
			(2
				(client setScript: foxWalk)
				(self dispose:)
			)
		)
	)
)

(instance foxTalker of Talker
	(properties
		x 10
		y 10
		view 1068
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2068)
		(PalVary PALVARYTARGET 2068)
		(AssertPalette 1068)
		(= font userFont)
		(super init: foxBust foxEye foxMouth &rest)
	)
)

(instance foxBust of Prop
	(properties
		view 1068
	)
)

(instance foxMouth of Prop
	(properties
		nsTop 56
		nsLeft 30
		view 1068
		loop 1
	)
)

(instance foxEye of Prop
	(properties
		nsTop 32
		nsLeft 38
		view 1068
		loop 2
	)
)
