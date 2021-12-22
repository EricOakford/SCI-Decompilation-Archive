;;; Sierra Script 1.0 - (do not remove this comment)
(script# 380)
(include sci.sh)
(use Main)
(use fileScr)
(use OccCyc)
(use LarryRoom)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm380 0
)

(local
	local0
	local1
	local2
	gEgoCel =  20
)
(instance rm380 of LarryRoom
	(properties
		noun 1
		picture 380
		horizon 0
	)
	
	(method (init)
		(if (== global100 380) (Bset 204) (Bclr 88))
		(theMusic stop:)
		(theMusic2 stop:)
		(if (and (Btst 204) (not (Btst 88)))
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 117 113 197 115 200 133 167 134 156 136 126 134 119 121
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 3
						init:
							0
							139
							210
							139
							200
							104
							218
							98
							213
							93
							197
							82
							174
							82
							170
							90
							135
							87
							96
							105
							75
							106
							68
							103
							45
							118
							20
							126
							0
							127
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 112 108 198 108 202 126 196 136 120 136 109 126
						yourself:
					)
			)
		)
		(super init: &rest)
		(theGame detailLevel: 4)
		(ego posn: 181 79 init: setSpeed: 6 normalize:)
		(bathDoor
			init:
			setPri: 80
			approachVerbs: 4
			ignoreActors: 1
		)
		(if global189 (Bset 88) (Bclr 197))
		(if (and (Btst 88) (not (Btst 197)))
			(thePearl
				init:
				approachVerbs: 5
				ignoreActors: 1
				setCycle: OccCyc self 1 50 150
			)
		)
		(cables init: ignoreActors: 1)
		(if (and (Btst 204) (not (Btst 88)))
			(grease init: ignoreActors: 1)
			(meter init: ignoreActors: 1)
			(if (> (theGame detailLevel:) 1)
				(toasterHeat init: setPri: 100 setCycle: Fwd)
			)
			(self setScript: doChardonnay)
		else
			(theMusic number: 380 play: setLoop: -1)
			(cabinetTop init: approachVerbs: 4)
			(cabinet init:)
			(table init:)
			(tanningBed init:)
			(transformer init:)
			(cables init:)
			(remoteCover init:)
			(if (Btst 88)
				(curRoom case: 2)
				(cabinet case: 2)
				(table case: 2)
				(tanningBed case: 2)
				(transformer case: 2)
				(cables case: 2)
			)
			(self setScript: enterRoom)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				local1
				(OneOf (ego cel?) 0 2)
				(not (sfx handle?))
				(!= gEgoCel (ego cel?))
			)
			(= gEgoCel (ego cel?))
			(larryShockFx play:)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (cue)
		(if (talkers size:) (messager cue: 1))
		(= gGButtonBarGetCursor theCursor)
		(theGame setCursor: normalCursor)
		(SetCursor 230 105)
		(if
			(Print
				width: 200
				font: userFont
				addTitle: 0 8 0 2 380
				addText: 0 8 0 1 50 2 380
				addIcon: 1911 0 0 0 0
				addButton: 0 13 8 0 1 50 33 380
				addButton: 1 12 8 0 1 145 33 380
				init:
			)
			(Bclr 74)
			(chardonnay setScript: 0)
			(chickInToaster setScript: 0)
			(theMusic stop:)
			(Bset 88)
			(if (not (Btst 196)) (theGame changeScore: 10 196))
			(if (not (ego has: 33)) (ego get: 33))
			(theGame hideControls: setCursor: waitCursor 1)
			(curRoom drawPic: -1)
			(cast eachElementDo: #hide)
			(curRoom newRoom: 620)
		else
			(= gLarryRoom curRoom)
			(theIconBar enableIcon: (ScriptID 0 8) show:)
			(theGame setCursor: gGButtonBarGetCursor)
		)
	)
)

(instance takePearlScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (!= (ego heading?) 270)
					(ego setHeading: 270 self)
				else
					(self cue:)
				)
			)
			(1 (= cycles 2))
			(2
				(if (Btst 74)
					(self cue:)
				else
					(ego view: 901 setLoop: 5 cel: 0 setCycle: CT 3 1 self)
				)
			)
			(3
				(ego get: 29)
				(theGame changeScore: 15 197)
				(thePearl dispose:)
				(= ticks 30)
			)
			(4
				(if (Btst 74)
					(self cue:)
				else
					(ego setCycle: End self)
				)
			)
			(5
				(if (Btst 74)
					(ego normalize: 353 1)
				else
					(ego normalize: 900 1)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance doChardonnay of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					normalize: 900 2
					cycleSpeed: 6
					moveSpeed: 6
					loop: 8
					cel: 2
					heading: 180
				)
				(= gLarryRoom curRoom)
				(theIconBar enableIcon: (ScriptID 0 8) show:)
				(= cycles 2)
			)
			(1
				(ego setLoop: 2 1 setMotion: MoveTo 172 94 self)
			)
			(2
				(bathDoor setCycle: Beg self)
			)
			(3 (= cycles 2))
			(4
				(sfx number: 33 play:)
				(= ticks 120)
			)
			(5
				(ego setLoop: 5 1 setMotion: MoveTo 146 106 self)
			)
			(6 (= cycles 2))
			(7 (messager say: 0 0 1 1 self))
			(8
				(ego setLoop: -1 setHeading: 180 self)
			)
			(9 (= ticks 90))
			(10 (ego setHeading: 125 self))
			(11 (= ticks 30))
			(12
				(chardonnay init: setCycle: Walk)
				(= cycles 10)
			)
			(13
				(sfx number: 32 play:)
				(bathDoor setCycle: End self)
			)
			(14 (ego setHeading: 45 self))
			(15
				(theMusic
					number: 337
					play:
					setLoop: -1
					mute: 1 4
					mute: 1 5
					mute: 1 6
					mute: 1 7
					mute: 1 8
					mute: 1 9
				)
				(= cycles 2)
			)
			(16
				(chardonnay
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 182 94 self
				)
			)
			(17 (= cycles 2))
			(18
				(bathDoor setCycle: Beg self)
			)
			(19
				(sfx number: 33 play:)
				(= ticks 30)
			)
			(20
				(messager say: 0 0 1 2 self)
			)
			(21
				(chardonnay setMotion: MoveTo 182 106 self)
			)
			(22
				(proc79_3 chardonnay ego self)
			)
			(23
				(chardonnay loop: 4 cel: 1)
				(= cycles 5)
			)
			(24
				(ego setHeading: 90)
				(theMusic mute: 0 8)
				(= cycles 2)
			)
			(25
				(messager say: 0 0 1 3 self)
			)
			(26
				(messager sayRange: 0 0 1 4 8 self)
				(theGame changeScore: 10 196)
				(theMusic mute: 0 4 mute: 0 9)
			)
			(27
				(ego setPri: 120 setMotion: MoveTo 162 125 self)
			)
			(28
				(ego setHeading: 180)
				(= cycles 5)
			)
			(29
				(chardonnay setScript: sGreaseLarry)
				(= cycles 5)
			)
			(30
				(sfx number: 234 play:)
				(ego
					view: 382
					loop: 2
					setCel: 0
					posn: 162 106
					setSpeed: 6
					setCycle: End self
				)
				(UnLoad 128 900)
			)
			(31
				(Bset 74)
				(UpdateScreenItem ((ScriptID 92 3) view: 1901))
				(cables setPri: 136)
				(ego loop: 0 cel: 0 setSpeed: 8 setCycle: CT 2 1 self)
				(UnLoad 128 900)
				(UnLoad 128 903)
			)
			(32 (ego setCycle: CT 6 1 self))
			(33
				(= local0 1)
				(sfx number: 814 play:)
				(ego setPri: 135 setCycle: End self)
			)
			(34 (self dispose:))
		)
	)
)

(instance sGreaseLarry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(chardonnay setMotion: PolyPath 114 134 self)
			)
			(1
				(chardonnay setLoop: 4 setCel: 2 posn: 114 134)
				(= cycles 2)
			)
			(2
				(if (not local0) (-- state))
				(= ticks 30)
			)
			(3
				(theMusic mute: 0 6 mute: 0 7)
				(messager sayRange: 0 0 1 9 10 self)
			)
			(4
				(ego setLoop: 1 setCel: 0 setCycle: CT 3 1 self)
			)
			(5
				(chardonnay
					view: 385
					setLoop: 0
					setCel: 0
					posn: 114 134
					setSpeed: 6
					setCycle: CT 6 1 self
				)
				(grease hide:)
				(UnLoad 128 383)
			)
			(6
				(sfx loop: 1 number: 632 play:)
				(chardonnay setCycle: End self)
			)
			(7
				(grease show:)
				(chardonnay
					loop: 1
					cel: 0
					posn: 109 134
					setCycle: End self
				)
			)
			(8
				(chardonnay
					loop: 2
					cel: 0
					posn: 114 134
					setCycle: CT 6 1 self
				)
			)
			(9
				(sfx loop: 1 number: 632 play:)
				(chardonnay setCycle: CT 0 -1 self)
			)
			(10
				(sfx loop: 1 number: 632 play:)
				(chardonnay setCycle: CT 6 1 self)
			)
			(11
				(sfx number: 632 play:)
				(chardonnay setCycle: End self)
			)
			(12 (= ticks 30))
			(13
				(theMusic mute: 0 5)
				(messager say: 0 0 1 11 self)
			)
			(14 (= ticks 30))
			(15
				(chardonnay setCycle: End self)
			)
			(16
				(chardonnay
					view: 383
					loop: 4
					cel: 2
					posn: 113 134
					setCycle: Walk
					setMotion: PolyPath 147 138 self
				)
				(UnLoad 128 385)
			)
			(17
				(chardonnay setHeading: 110)
				(= cycles 5)
			)
			(18
				(chardonnay
					view: 385
					loop: 3
					cel: 0
					posn: 149 138
					setSpeed: 9
					setCycle: End self
				)
				(UnLoad 128 383)
			)
			(19
				(chardonnay
					loop: 4
					posn: 149 138
					cel: 0
					setCycle: CT 3 1 self
				)
			)
			(20
				(UpdateScreenItem ((ScriptID 92 3) view: 1904 cel: 1))
				(sfx number: 632 play:)
				(chardonnay setCycle: CT 6 1 self)
			)
			(21
				(sfx number: 632 play:)
				(chardonnay setCycle: CT 9 1 self)
			)
			(22
				(sfx number: 632 play:)
				(chardonnay setCycle: End self)
			)
			(23
				(sfx number: 632 play:)
				(chardonnay setCycle: CT 9 -1 self)
			)
			(24
				(sfx number: 632 play:)
				(chardonnay setCycle: CT 6 -1 self)
			)
			(25
				(sfx number: 632 play:)
				(chardonnay setCycle: CT 3 -1 self)
			)
			(26
				(sfx number: 632 play:)
				(chardonnay setCycle: Beg self)
			)
			(27
				(sfx number: 632 play:)
				(UpdateScreenItem ((ScriptID 92 3) view: 1901 loop: 1))
				(chardonnay loop: 3 cel: 3 setCycle: Beg self)
			)
			(28 (= ticks 90))
			(29
				(curRoom setScript: sHookUpLarry)
				(self dispose:)
			)
		)
	)
)

(instance sHookUpLarry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(messager say: 0 0 1 12 self)
			)
			(2
				(chardonnay
					view: 386
					loop: 0
					cel: 0
					posn: 151 138
					cycleSpeed: 8
					setCycle: CT 4 1 self
				)
				(UnLoad 128 385)
			)
			(3
				(cables hide: dispose:)
				(chardonnay setCycle: End self)
			)
			(4
				(chardonnay loop: 1 cel: 0 setCycle: CT 4 1 self)
			)
			(5 (sfx number: 383 play: self))
			(6
				(messager say: 0 0 1 13 self)
			)
			(7 (= ticks 30))
			(8
				(chardonnay setCycle: CT 8 1 self)
			)
			(9 (sfx number: 383 play: self))
			(10
				(messager say: 0 0 1 14 self)
			)
			(11 (= ticks 30))
			(12
				(chardonnay setCycle: CT 11 1 self)
			)
			(13
				(sfx number: 383 play: self)
			)
			(14
				(messager say: 0 0 1 15 self)
			)
			(15 (= ticks 30))
			(16
				(chardonnay setCycle: End self)
			)
			(17
				(ego view: 381 setLoop: 1 1 setCel: 0 x: 156 y: 126)
				(= ticks 30)
			)
			(18
				(messager sayRange: 0 0 1 16 18 self)
			)
			(19 (= ticks 90))
			(20
				(chardonnay
					view: 387
					loop: 0
					cel: 0
					x: 150
					y: 136
					cycleSpeed: 12
					setCycle: End self
				)
				(UnLoad 128 386)
			)
			(21
				(messager say: 0 0 1 19 self)
			)
			(22
				(chardonnay
					loop: 1
					cel: 0
					x: 150
					y: 136
					cycleSpeed: 8
					setCycle: End self
				)
				(sfx number: 330 play:)
			)
			(23
				(switch1
					init:
					setPri: 131
					cel: 0
					ignoreActors: 1
					setCycle: CT 5 1 self
				)
			)
			(24
				(sfx number: 383 play:)
				(switch1 setCycle: End self)
			)
			(25
				(sfxMachine play: hold: 1)
				(if (cast contains: toasterHeat) (toasterHeat dispose:))
				(= cycles 2)
			)
			(26
				(messager say: 0 0 1 20 self)
			)
			(27
				(= next sStartTheMachine)
				(self dispose:)
			)
		)
	)
)

(instance sStartTheMachine of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(chardonnay cel: 0 setCycle: End self)
				(++ local2)
				(sfx number: 330 play:)
			)
			(1
				(meter setCycle: CT (+ (meter cel?) 1) 1 self)
				(sfxMachine hold: (+ local2 1))
			)
			(2
				(switch local2
					(1
						(light1 init: setPri: 180 ignoreActors: 1 setCycle: Fwd)
						(if (> (theGame detailLevel:) 1)
							(light2 init: setPri: 131 ignoreActors: 1 setCycle: Fwd)
						)
						(= seconds 2)
					)
					(2
						(light3 init: setPri: 131 ignoreActors: 1 setCycle: Fwd)
						(if (> (theGame detailLevel:) 1)
							(panel init: ignoreActors: 1 setCycle: Fwd)
						)
						(= seconds 2)
					)
					(3
						(if (> (theGame detailLevel:) 1)
							(bigshock
								init:
								setPri: 180
								ignoreActors: 1
								setScript: sDoShock
							)
						)
						(switchSpark
							init:
							setPri: 131
							ignoreActors: 1
							setCycle: OccCyc self 1 10 100
						)
						(switch1 dispose:)
						(= seconds 2)
					)
					(4
						(biglights
							init:
							setPri: 180
							ignoreActors: 1
							setCycle: Fwd
						)
						(= seconds 2)
					)
					(5
						(if (> (theGame detailLevel:) 1)
							(motor init: ignoreActors: 1 setCycle: Fwd)
						)
						(= seconds 2)
					)
					(6
						(if (> (theGame detailLevel:) 1)
							(bigshake
								init:
								ignoreActors: 1
								setCycle: OccCyc self 1 10 60
							)
						)
						(= seconds 2)
					)
				)
			)
			(3
				(switch local2
					(1
						(messager sayRange: 0 0 1 21 23 self)
					)
					(2
						(messager sayRange: 0 0 1 24 26 self)
					)
					(6
						(messager sayRange: 0 0 1 27 28 self)
					)
					(else  (= cycles 1))
				)
			)
			(4 (= cycles 10))
			(5
				(if (<= local2 5)
					(self changeState: 0)
				else
					(= next sPlugInCord)
					(self dispose:)
				)
			)
		)
	)
)

(instance sPlugInCord of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager sayRange: 0 0 1 29 30 self)
			)
			(1
				(ego
					view: 384
					setLoop: 0 1
					setCel: 0
					x: 137
					y: 60
					setCycle: 0
				)
				(chardonnay loop: 0 cel: 6 setCycle: Beg self)
			)
			(2
				(chardonnay
					view: 383
					setSpeed: 6
					setCycle: Walk
					setMotion: PolyPath 220 137 self
				)
				(UnLoad 128 387)
			)
			(3
				(chardonnay
					view: 388
					loop: 0
					cel: 0
					setSpeed: 8
					setCycle: CT 2 1 self
				)
				(UnLoad 128 383)
			)
			(4
				(messager say: 0 0 1 31 self)
			)
			(5
				(UpdateScreenItem ((ScriptID 92 3) view: 1903 cel: 1))
				(messager say: 0 0 3 1 self)
			)
			(6
				(chardonnay setCycle: CT 5 1 self)
				(theMusic number: 338 setLoop: 1 play:)
			)
			(7
				(motor dispose:)
				(bigshake dispose:)
				(bigshock setScript: 0 dispose:)
				(sfxMachine2 stop:)
				(= cycles 2)
			)
			(8
				(sfx loop: 1 number: 385 play: self)
				(ego
					view: 384
					loop: 0
					cel: 0
					x: 137
					y: 60
					cycleSpeed: 9
					setCycle: End self
				)
			)
			(9)
			(10
				(= local1 1)
				(grease dispose:)
				(meter setCycle: End)
				(ego loop: 1 cel: 0 cycleSpeed: 20 setCycle: Fwd)
				(messager say: 0 0 3 2 self)
			)
			(11
				((ScriptID 92 3) view: 1903 cel: 1)
				(UnLoad 128 396)
				(UnLoad 128 381)
				(= ticks 240)
			)
			(12
				(chickInToaster setScript: sGirlRunsOut)
			)
			(13
				(sfx number: 330 play:)
				(plug init:)
				(chardonnay setCycle: End self)
			)
			(14
				(chardonnay
					loop: 1
					cel: 0
					x: 217
					y: 137
					setCycle: CT 7 1 self
				)
			)
			(15
				(= local1 0)
				(messager say: 0 0 3 5 self)
				(chardonnay setCycle: End self)
			)
			(16)
			(17
				(= gLarryRoom 0)
				(theIconBar disableIcon: (ScriptID 0 8))
				(ego get: 33)
				(Bset 88)
				(messager say: 0 0 3 6 self)
			)
			(18
				(= local1 1)
				(= next sEndCartoon)
				(self dispose:)
			)
		)
	)
)

(instance sEndCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame hideControls:)
				(= cycles 2)
			)
			(1
				(sfxMachine fade:)
				(cast eachElementDo: #hide)
				(ego show:)
				(thePlane drawPic: -1)
				(= ticks 90)
			)
			(2
				(medallion init: setCycle: End self)
			)
			(3
				(larryShockFx play:)
				(ego hide:)
				(= seconds 2)
			)
			(4
				(= local1 0)
				(Print font: userFont addText: 0 0 3 7 modeless: 2 init:)
				(= ticks 360)
			)
			(5
				(Print modeless: 0)
				(if (Print dialog?) ((Print dialog?) dispose:))
				(= cycles 2)
			)
			(6
				(Bclr 74)
				(curRoom newRoom: 620)
				(self dispose:)
			)
		)
	)
)

(instance sGirlRunsOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(chickInToaster init: setPri: 96 ignoreActors: 1)
				(messager say: 0 0 3 3 self)
			)
			(1
				(chickInToaster setCycle: End self)
			)
			(2
				(toasterTop init: setPri: 90 ignoreActors: 1)
				(chickInToaster
					loop: 1
					cel: 0
					x: 97
					y: 95
					setCycle: End self
				)
			)
			(3 (= local1 0) (= ticks 30))
			(4 (messager say: 0 0 3 4 self))
			(5
				(= local1 1)
				(chickInToaster
					setLoop: 2 1
					setPri: -1
					setCel: 0
					x: 97
					y: 95
					xStep: 10
					yStep: 2
					cycleSpeed: 2
					moveSpeed: 2
					setCycle: Walk
					setMotion: MoveTo 250 67 self
				)
				(thePearl loop: 1 cel: 0 posn: 172 61 init: setCycle: End)
				(bathDoor setCycle: End self)
				(sfx number: 32 play:)
			)
			(6)
			(7
				(thePearl loop: 0 cel: 0 posn: 116 95 setCycle: Fwd)
				(bathDoor setCycle: Beg self)
			)
			(8
				(sfx number: 33 play:)
				(toasterTop setCycle: Beg self)
			)
			(9
				(sPlugInCord cue:)
				(chickInToaster dispose:)
				(toasterTop dispose:)
				(= cycles 3)
			)
			(10
				(UnLoad 128 398)
				(self dispose:)
			)
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 182 94 self)
			)
			(1
				(bathDoor setCycle: Beg self)
			)
			(2
				(sfx number: 33 play:)
				(if (Btst 74)
					(ego normalize: 353 2)
				else
					(ego normalize: 900 2)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitToMudbaths of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 0 self)
				(= cycles 2)
			)
			(1)
			(2
				(sfx number: 32 play:)
				(bathDoor setCycle: End self)
			)
			(3
				(ego setMotion: MoveTo 181 79 self)
			)
			(4 (= cycles 10))
			(5
				(curRoom newRoom: 400)
				(self dispose:)
			)
		)
	)
)

(instance sDoShock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sfxMachine2 play:)
				(bigshock setCycle: Fwd)
				(= ticks (Random 30 60))
			)
			(1
				(bigshock setCycle: End self)
			)
			(2
				(= ticks (Random 50 150))
				(sfxMachine2 stop:)
			)
			(3 (self changeState: 0))
		)
	)
)

(instance chardonnay of Actor
	(properties
		x 182
		y 79
		view 383
		loop 4
		cel 2
		xStep 5
	)
)

(instance chickInToaster of Actor
	(properties
		x 95
		y 95
		view 398
	)
)

(instance toasterHeat of Prop
	(properties
		x 69
		y 71
		view 380
		loop 5
	)
)

(instance bathDoor of Prop
	(properties
		noun 2
		sightAngle 40
		approachX 182
		approachY 94
		x 183
		y 80
		priority 85
		fixPriority 1
		view 3800
		cel 2
	)
	
	(method (init)
		(super init: &rest)
		(self signal: (| (self signal?) $1000))
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: sExitToMudbaths)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance toasterTop of Prop
	(properties
		x 95
		y 95
		view 397
		cel 2
	)
)

(instance thePearl of Prop
	(properties
		noun 3
		approachX 125
		approachY 97
		x 116
		y 95
		view 380
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: takePearlScr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance medallion of Prop
	(properties
		x 137
		y 135
		view 389
	)
)

(instance switch1 of Prop
	(properties
		x 241
		y 113
		view 395
		loop 3
	)
)

(instance meter of Prop
	(properties
		x 241
		y 139
		view 395
		loop 5
	)
)

(instance light1 of Prop
	(properties
		x 248
		y 114
		view 395
	)
)

(instance light2 of Prop
	(properties
		x 225
		y 111
		view 395
		loop 1
		detailLevel 2
	)
)

(instance light3 of Prop
	(properties
		x 218
		y 85
		view 395
		loop 2
	)
)

(instance panel of Prop
	(properties
		x 235
		y 132
		view 395
		loop 7
		detailLevel 2
	)
)

(instance bigshock of Prop
	(properties
		x 235
		y 54
		view 396
		detailLevel 2
	)
)

(instance switchSpark of Prop
	(properties
		x 241
		y 113
		view 395
		loop 4
	)
)

(instance biglights of Prop
	(properties
		x 274
		y 139
		view 395
		loop 6
	)
)

(instance motor of Prop
	(properties
		x 263
		y 67
		view 396
		loop 1
		detailLevel 2
	)
)

(instance bigshake of Prop
	(properties
		x 308
		y 79
		view 396
		loop 8
		detailLevel 2
	)
)

(instance plug of View
	(properties
		x 221
		y 138
		view 380
		loop 7
	)
)

(instance grease of View
	(properties
		x 124
		y 108
		view 380
		loop 2
	)
)

(instance remoteCover of View
	(properties
		noun 4
		sightAngle 40
		x 153
		y 124
		view 380
		loop 3
	)
)

(instance cables of View
	(properties
		noun 6
		sightAngle 40
		x 144
		y 131
		z 67
		view 380
		loop 8
	)
)

(instance cabinetTop of Feature
	(properties
		noun 10
		sightAngle 40
		approachX 47
		approachY 125
		x 37
		y 109
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						3
						98
						3
						88
						15
						85
						16
						93
						20
						91
						21
						87
						25
						87
						26
						91
						33
						89
						27
						82
						30
						80
						37
						87
						39
						87
						40
						77
						51
						73
						53
						83
						65
						79
						64
						83
						56
						88
						41
						93
						11
						101
						0
						101
						0
						98
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance cabinet of Feature
	(properties
		noun 9
		sightAngle 40
		x 37
		y 108
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 101 65 81 68 95 58 106 38 116 14 122 0 123
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance table of Feature
	(properties
		noun 4
		sightAngle 40
		x 154
		y 123
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 124 109 127 104 131 100 132 103 188 104 193 113 179 132 136 132
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance tanningBed of Feature
	(properties
		noun 7
		sightAngle 40
		x 101
		y 79
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						75
						67
						87
						61
						96
						60
						113
						60
						125
						66
						127
						85
						94
						101
						80
						100
						67
						95
						67
						79
						69
						74
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance transformer of Feature
	(properties
		noun 5
		sightAngle 40
		x 261
		y 109
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						319
						0
						319
						138
						234
						137
						220
						118
						220
						93
						216
						89
						215
						82
						218
						71
						208
						67
						209
						64
						217
						59
						218
						50
						205
						50
						199
						44
						199
						40
						206
						36
						281
						16
						213
						14
						227
						0
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance sfx of Sound
	(properties
		flags $0001
	)
)

(instance larryShockFx of Sound
	(properties
		flags $0001
		number 651
	)
)

(instance sfxMachine of Sound
	(properties
		flags $0001
		number 381
		loop -1
	)
)

(instance sfxMachine2 of Sound
	(properties
		flags $0001
		number 822
		loop -1
	)
)
