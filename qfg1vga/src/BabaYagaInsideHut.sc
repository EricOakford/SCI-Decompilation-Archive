;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
(include game.sh) (include "21.shm")
(use Main)
(use Procs)
(use Print)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm21 0
	babaTalker 1
)

;Yes or No
(enum
	sayYes
	sayNo
)


(local
	local0
	babaSpeaks
	seenWindow
	gEgoCycleSpeed
	gEgoMoveSpeed
)
(procedure (BabaKillsEgo)
	(User canInput: FALSE)
	(switch babaState
		(babaNAME (rm21 setScript: nameDeath))
		(babaBRAVE (rm21 setScript: braveDeath))
		(babaFETCH
			(rm21 setScript: noFetchDeath)
		)
		(babaBRING
			(rm21 setScript: noBringDeath)
		)
	)
)

(instance rm21 of Room
	(properties
		noun N_ROOM
		picture 21
		style VSHUTTER
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						51 164
						99 166
						145 189
						0 189
						0 0
						319 0
						319 189
						228 189
						229 183
						192 160
						216 149
						160 127
						44 132
					yourself:
				)
		)
		(LoadMany VIEW 20 21 19 531 122)
		(LoadMany SOUND 51 28)
		(LoadMany SCRIPT DCICON)
		(super init:)
		(SolvePuzzle f21EnterHut 2)
		(if (ego has: iMandrake)
			(Bclr fBabaCurse)
		)
		(cSound fade:)
		(teleport number: 28 init:)
		(Bclr fBabaHutOpen)
		(bubbleMusic init: play:)
		(NormalEgo)
		(= yesNoTimer 0)
		(features
			add:
				skull
				cauldron
				broom
				note
				bucket
				table
				web
				firePlace
				telephone
				murial
				brokenGate
				hutWindow
		)
		
		;UPGRADE
;;;		(skull init:)
;;;		(cauldron init:)
;;;		(broom init:)
;;;		(note init:)
;;;		(bucket init:)
;;;		(table init:)
;;;		(web init:)
;;;		(firePlace init:)
;;;		(telephone init:)
;;;		(murial init:)
;;;		(brokenGate init:)
;;;		(hutWindow init:)
		
		(if (== prevRoomNum 299)
			(ego init: posn: 166 187)
		else
			(ego loop: 1 posn: 202 187 init:)
		)
		(spider setCycle: Forward init: stopUpd:)
		(bat init:)
		(= gEgoCycleSpeed (ego cycleSpeed?))
		(= gEgoMoveSpeed (ego moveSpeed?))
		(fire setCycle: Forward init:)
		(cond 
			((not (Btst fBeenIn21))
				(self setScript: firstWitch)
			)
			((== babaState babaFETCH)
				(= babaState babaBRING)
				(self setScript: nextWitch)
			)
			((ego has: iMagicMirror)
				(= babaState babaFINALE)
				(self setScript: lastWitch)
			)
			((== babaState babaBRING)
				(= babaState babaFINALE)
				(self setScript: lastWitch)
			)
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn21)
		(ego moveSpeed: gEgoMoveSpeed cycleSpeed: gEgoCycleSpeed)
		(DisposeScript REVERSE)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_ROOM V_LOOK C_LOOK_ROOM 0 self)
			)
			(V_MAGICMIRROR
				(messager say: N_BABA V_MAGICMIRROR NULL)
				(Bset fBabaFrog)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance web of Feature
	(properties
		x 68
		y 167
		noun N_WEB
		onMeCheck cBLUE
	)
)

(instance table of Feature
	(properties
		x 270
		y 163
		noun N_TABLE
		onMeCheck cGREEN
	)
)

(instance bucket of Feature
	(properties
		x 221
		y 164
		noun N_BUCKET
		onMeCheck cCYAN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_BUCKET V_LOOK)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance note of Feature
	(properties
		x 242
		y 74
		noun N_NOTE
		onMeCheck cRED
	)
)

(instance firePlace of Feature
	(properties
		x 282
		y 85
		noun N_FIREPLACE
		onMeCheck cMAGENTA
	)
)

(instance broom of Feature
	(properties
		x 306
		y 141
		noun N_BROOM
		onMeCheck cBROWN
	)
)

(instance cauldron of Feature
	(properties
		x 209
		y 130
		noun N_CAULDRON
		onMeCheck cLGREY
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_CAULDRON V_LOOK)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance skull of Feature
	(properties
		x 228
		y 50
		noun N_SKULL
		onMeCheck cGREY
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_SKULL V_LOOK)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance telephone of Feature
	(properties
		x 226
		y 128
		z 73
		noun N_TELEPHONE
		nsTop 50
		nsLeft 221
		nsBottom 60
		nsRight 232
		sightAngle 40
	)
)

(instance murial of Feature
	(properties
		x 119
		noun N_MURAL
		nsTop 4
		nsLeft 5
		nsBottom 117
		nsRight 233
		sightAngle 40
	)
)

(instance brokenGate of Feature
	(properties
		x 34
		y 141
		noun N_BROKEN_GATE
		nsTop 117
		nsLeft 3
		nsBottom 188
		nsRight 66
		sightAngle 40
	)
)

(instance hutWindow of Feature
	(properties
		x 177
		y 181
		noun N_WINDOW
		nsTop 176
		nsLeft 161
		nsBottom 187
		nsRight 193
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(switch seenWindow
				(0
					(messager say: N_WINDOW V_LOOK C_WINDOW3)
					(++ seenWindow)
				)
				(1
					(messager say: N_WINDOW V_LOOK C_WINDOW1)
					(++ seenWindow)
				)
				(2
					(messager say: N_WINDOW V_LOOK C_WINDOW2)
				)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance reflection of Prop
	(properties
		view 122
		loop 1
		cycleSpeed 1
	)
)

(instance spider of Prop
	(properties
		x 86
		y 171
		noun N_SPIDER
		view 21
		priority pWHITE
		signal fixPriOn
		cycleSpeed 12
	)
)

(instance TP of Prop
	(properties
		view 531
	)
)

(instance fire of Prop
	(properties
		x 273
		y 143
		view 21
		loop 3
	)
	
	(method (doVerb theVerb)
		(cauldron doVerb: theVerb &rest)
	)
)

(instance bat of Prop
	(properties
		x 62
		y 18
		noun N_BAT
		view 21
		loop 7
		priority pWHITE
		signal fixPriOn
		cycleSpeed 8
	)
)

(instance baba of Actor
	(properties
		noun N_BABA
		view 20
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_BABA V_LOOK)
			)
			(V_MAGICMIRROR
				(messager say: N_BABA V_MAGICMIRROR NULL)
				(Bset fBabaFrog)
			)
			(else 
				(lastWitch seconds: 0 cue:)
			)
		)
	)
)

(instance firstWitch of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 143 172 self)
			)
			(1
				(= seconds 2)
			)
			(2
				(TP posn: 195 132 setPri: 15 init: setCycle: CycleTo 4 1 self)
			)
			(3
				(teleport play:)
				(TP setCel: 4 setCycle: CycleTo 9 1 self)
				(baba posn: 195 132 setLoop: 6 init:)
			)
			(4
				(TP setCel: 0 dispose:)
				(self cue:)
			)
			(5
				(spider startUpd:)
				(= babaSpeaks TRUE)
				(messager say: N_ROOM NULL NULL 1 self)
			)
			(6
				(= babaSpeaks FALSE)
				(bat setCycle: EndLoop self)
			)
			(7
				(ego loop: 0)
				(spider setCycle: 0)
				(= babaSpeaks TRUE)
				(messager say: N_ROOM NULL NULL 2 self)
			)
			(8
				(= babaSpeaks FALSE)
				(bat setLoop: 5)
				(baba setLoop: 6 setCycle: EndLoop self)
			)
			(9
				(messager say: N_ROOM NULL C_FROZEN_SPELL 0 self)
			)
			(10
				(baba
					setLoop: 8
					xStep: 2
					yStep: 5
					setCycle: Walk
					setMotion: PolyPath (+ (ego x?) 20) (- (ego y?) 5) self
				)
			)
			(11
				(baba setCycle: 0)
				(= cycles 2)
			)
			(12
				(messager say: N_ROOM NULL NULL 3 self)
				(= babaSpeaks TRUE)
			)
			(13
				(spider setCycle: Forward)
				(bat cycleSpeed: 12 setCycle: Forward)
				(= seconds 4)
			)
			(14
				(bat setCycle: 0)
				(spider setCycle: 0)
				(messager say: N_ROOM NULL NULL 4 self)
			)
			(15
				(messager say: N_ROOM NULL C_FROG_LEGS 0 self)
			)
			(16
				(spider setCycle: Forward)
				(bat setCycle: Forward)
				(= seconds 3)
			)
			(17
				(messager say: N_ROOM NULL NULL 5 self)
				(bat setCycle: 0)
				(spider setCycle: 0)
			)
			(18
				(messager say: N_ROOM NULL NULL 6 self)
			)
			(19
				(= babaSpeaks FALSE)
				(baba setLoop: 6 setCycle: EndLoop self)
			)
			(20
				(ego view: 19 loop: 2 cel: 0 setCycle: EndLoop self)
				(teleport play:)
			)
			(21
				(baba
					setLoop: 8
					setCycle: Walk
					setMotion: MoveTo 160 168 self
				)
			)
			(22
				(ego ignoreActors: hide:)
				(baba view: 20 setLoop: 7 setCycle: CycleTo 3 1 self)
			)
			(23 (baba setCycle: EndLoop self))
			(24
				(spider setCycle: Forward)
				(bat setCycle: Forward)
				(= babaSpeaks TRUE)
				(messager say: N_ROOM NULL NULL 7 self)
			)
			(25
				(= babaSpeaks FALSE)
				(spider setCycle: 0)
				(bat setCycle: 0)
				(rm21 setScript: firstTalk)
			)
		)
	)
)

(instance firstTalk of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(baba
					setLoop: 0
					xStep: 3
					yStep: 9
					ignoreActors:
					setCycle: Walk
					setMotion: MoveTo 276 144 self
				)
			)
			(1
				(baba
					view: 20
					setLoop: 3
					setCycle: 0
					ignoreActors:
					setPri: 13
					cel: 0
				)
				(ego
					view: 20
					setLoop: 2
					cel: 0
					posn: 263 97
					setPri: (+ (baba priority?) 1)
					setCycle: Forward
					ignoreActors:
					show:
				)
				(spider setCycle: Forward)
				(bat setCycle: Forward)
				(= seconds 3)
			)
			(2
				(= babaSpeaks TRUE)
				(messager say: N_ROOM NULL NULL 8 self)
				(ego setCycle: 0)
			)
			(3
				(spider setCycle: 0)
				(bat setLoop: 1 cel: 4 setCycle: BegLoop self)
			)
			(4
				(messager say: N_ROOM NULL NULL 9 self)
			)
			(5 (self cue:))
			(6
				(= babaState babaNAME)
				(messager say: N_ROOM NULL C_ASK_NAME 0 self)
			)
			(7
				(switch
					(Print
						addButton: sayYes N_ROOM NULL NULL 12 0 0 21
						addButton: sayNo N_ROOM NULL NULL 13 0 18 21
						init:
					)
					(sayYes
						(= babaSpeaks FALSE)
						(= ticks 1)
					)
					(sayNo
						(BabaKillsEgo)
					)
				)
			)
			(8
				(ego setCycle: Forward)
				(= babaSpeaks TRUE)
				(self cue:)
			)
			(9
				(messager say: N_ROOM NULL C_YES_NAME 0 self)
			)
			(10
				(ego setCycle: 0)
				(baba setPri: 14)
				(= babaSpeaks TRUE)
				(messager say: N_ROOM NULL NULL 10 self)
			)
			(11 (= seconds 2))
			(12
				(= babaState babaBRAVE)
				(messager say: N_ROOM NULL NULL 11 self)
			)
			(13
				(switch
					(Print
						addButton: sayYes N_ROOM NULL NULL 12 0 0 21
						addButton: sayNo N_ROOM NULL NULL 13 0 18 21
						init:
					)
					(sayYes
						(= babaSpeaks FALSE)
						(= ticks 1)
					)
					(sayNo (BabaKillsEgo))
				)
			)
			(14
				(ego setCycle: Forward)
				(= babaSpeaks TRUE)
				(self cue:)
			)
			(15
				(messager say: N_ROOM NULL NULL 14 self)
			)
			(16
				(ego setCycle: 0)
				(messager say: N_ROOM NULL NULL 15 self)
			)
			(17
				(messager say: N_ROOM NULL NULL 16 self)
			)
			(18
				(ego setCycle: Forward)
				(self cue:)
			)
			(19
				(= babaState babaFETCH)
				(messager say: N_ROOM NULL NULL 17 self)
			)
			(20
				(switch
					(Print
						addButton: sayYes N_ROOM NULL NULL 12 0 0 21
						addButton: sayNo N_ROOM NULL NULL 13 0 18 21
						init:
					)
					(sayYes
						(= babaSpeaks FALSE)
						(= ticks 1)
					)
					(sayNo (BabaKillsEgo))
				)
			)
			(21
				(ego setCycle: Forward)
				(= babaSpeaks TRUE)
				(self cue:)
			)
			(22
				(messager say: N_ROOM NULL NULL 18 self)
			)
			(23
				(ego setCycle: 0)
				(spider setCycle: Forward)
				(bat setLoop: 5 setCycle: Forward)
				(messager say: N_ROOM NULL NULL 19 self)
			)
			(24
				(ego setCycle: 0)
				(self cue:)
			)
			(25
				(spider setCycle: 0)
				(bat setCycle: 0)
				(messager say: N_ROOM NULL NULL 20 self)
			)
			(26
				(messager say: N_ROOM NULL NULL 21 self)
			)
			(27
				(messager say: N_ROOM NULL NULL 22 self)
			)
			(28
				(= babaSpeaks FALSE)
				(ego setCycle: 0 hide:)
				(baba
					view: 20
					setLoop: 1
					xStep: 4
					yStep: 8
					setPri: 10
					setCycle: Walk
					setMotion: PolyPath 123 133 self
				)
			)
			(29
				(baba view: 20 setLoop: 7 cel: 5 setCycle: CycleTo 3 -1 self)
			)
			(30
				(spider setCycle: Forward)
				(bat setCycle: Forward)
				(ego
					view: 19
					setLoop: 2
					cel: 8
					posn: (- (baba x?) 17) (+ (baba y?) 4)
					setPri: 10
					show:
				)
				(baba setCycle: CycleTo 0 -1 self)
			)
			(31
				(messager say: N_ROOM NULL NULL 23 self)
			)
			(32
				(baba setLoop: 6 cel: 0 setCycle: EndLoop self)
				(TP
					posn: (ego x?) (ego y?)
					setPri: 11
					init:
					setCycle: CycleTo 5 1 self
				)
				(teleport play:)
			)
			(33
				(ego hide:)
				(TP setCycle: EndLoop self)
			)
			(34 (curRoom newRoom: 22))
		)
	)
)

(instance nameDeath of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(baba setCel: 1 setPri: 13 setScript: 0)
				(= yesNoTimer 0)
				(messager say: N_ROOM NULL C_NO_NAME 1 self)
			)
			(1
				(messager say: N_ROOM NULL NULL 24 self)
			)
			(2
				(spider setCycle: Forward)
				(bat setCycle: Forward)
				(= seconds 3)
			)
			(3
				(baba setCel: 1)
				(messager say: N_ROOM NULL NULL 25 self)
			)
			(4 (EgoDead 80 81))
		)
	)
)

(instance braveDeath of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(baba setScript: 0)
				(= yesNoTimer 0)
				(messager say: N_ROOM NULL NULL 26 self)
			)
			(1
				(baba setCel: 1)
				(spider setCycle: Forward)
				(bat setCycle: Forward)
				(messager say: N_ROOM NULL C_NO_BRAVE 0 self)
			)
			(2 (EgoDead 39 40))
		)
	)
)

(instance noFetchDeath of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(baba setScript: 0)
				(messager say: N_ROOM NULL C_NO_FETCH 0 self)
			)
			(1
				(baba setCel: 1)
				(spider setCycle: Forward)
				(bat setCycle: Forward)
				(messager say: N_ROOM NULL NULL 27 self)
			)
			(2 (EgoDead 78 79))
		)
	)
)

(instance noBringDeath of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(baba setScript: 0)
				(messager say: N_ROOM NULL NULL 28 self)
			)
			(1
				(spider setCycle: Forward)
				(bat setCycle: Forward)
				(= seconds 3)
			)
			(2
				(messager say: N_ROOM NULL NULL 29 self)
			)
			(3 (EgoDead 17 18))
		)
	)
)

(instance lastWitch of Script
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 120 150 self)
			)
			(1 (= seconds 2))
			(2
				(TP posn: 195 132 init: setCycle: CycleTo 4 1 self)
			)
			(3
				(teleport play:)
				(TP setCel: 4 setCycle: CycleTo 9 1 self)
				(baba setLoop: 6 posn: 195 132 init:)
			)
			(4
				(TP setCel: 0 dispose:)
				(spider startUpd:)
				(= babaSpeaks TRUE)
				(if (Btst fBeenIn21)
					(messager say: N_ROOM NULL NULL 30 self)
				else
					(messager say: N_ROOM NULL NULL 31 self)
				)
			)
			(5 (bat setCycle: EndLoop self))
			(6
				(HandsOn)
				(theIconBar disable: ICON_WALK ICON_DO ICON_TALK ICON_ACTIONS ICON_CAST)
				(user canControl: FALSE)
				(user canInput: TRUE)
				(ego loop: 0)
				(= babaSpeaks FALSE)
				(= seconds 10)
			)
			(7
				(= isHandsOff FALSE)
				(HandsOff)
				(bat setCycle: 0)
				(spider setCycle: 0)
				(= babaSpeaks TRUE)
				(messager say: N_ROOM NULL NULL 32 self)
			)
			(8
				(theIconBar disable: ICON_WALK ICON_DO ICON_TALK ICON_ACTIONS ICON_CAST)
				(if (Btst fBabaFrog)
					(client setScript: endGame)
				else
					(= babaSpeaks FALSE)
					(baba setCycle: EndLoop self)
				)
			)
			(9
				(ego view: 19 setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(10
				(spider setCycle: 0)
				(baba
					setLoop: 8
					xStep: 2
					yStep: 5
					setCycle: Walk
					setMotion: PolyPath (+ (ego x?) 20) (- (ego y?) 5) self
				)
			)
			(11
				(= babaSpeaks TRUE)
				(messager say: N_ROOM NULL NULL 33 self)
			)
			(12
				(baba setLoop: 6 setCycle: 0)
				(EgoDead 138 139)
			)
		)
	)
)

(instance endGame of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= babaState babaFINALE)
				(baba
					view: 20
					setLoop: 6
					cycleSpeed: 18
					setCycle: CycleTo 3 1 self
				)
				(ego view: 122 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(1)
			(2
				(baba setCycle: EndLoop)
				(reflection
					setLoop: 1
					cel: 0
					posn: 132 107
					setPri: (+ (ego priority?) 1)
					init:
					setCycle: EndLoop self
				)
			)
			(3
				(ego setCycle: BegLoop)
				(baba
					view: 19
					setLoop: 0
					posn: (baba x?) (+ (baba y?) 2)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(baba setLoop: 1 setCycle: Forward)
				(SolvePuzzle f21BabaFrog 50)
				(= seconds 3)
			)
			(5
				(messager say: N_ROOM NULL C_BABA_FROG 0 self)
			)
			(6
				(messager say: N_ROOM NULL NULL 34 self)
			)
			(7
				(NormalEgo)
				(Face ego baba)
				(messager say: N_ROOM NULL NULL 35 self)
			)
			(8
				(teleport play:)
				(TP
					posn: (ego x?) (ego y?)
					setPri: 15
					cel: 0
					init:
					setCycle: CycleTo 6 1 self
				)
			)
			(9
				(ego dispose:)
				(TP setCycle: EndLoop self)
			)
			(10
				(TP dispose:)
				(Bset fBabaHutOpen)
				(curRoom newRoom: 22)
			)
		)
	)
)

(instance nextWitch of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 143 172 self)
			)
			(1 (= seconds 2))
			(2
				(TP posn: 195 132 setPri: 15 init: setCycle: CycleTo 4 1 self)
			)
			(3
				(teleport play:)
				(baba setLoop: 6 posn: 195 132 init:)
				(TP setCel: 4 setCycle: CycleTo 9 1 self)
			)
			(4
				(TP setCel: 0 dispose:)
				(teleport play:)
				(bat setCycle: EndLoop self)
			)
			(5
				(messager say: N_ROOM NULL NULL 36 self)
				(spider startUpd:)
				(= babaSpeaks TRUE)
			)
			(6 (ego loop: 0) (= seconds 2))
			(7
				(bat setCycle: 0)
				(spider setCycle: 0)
				(messager say: N_ROOM NULL C_BABA_2ND_TIME 1 self)
			)
			(8
				(= babaSpeaks FALSE)
				(baba setCycle: EndLoop)
				(= seconds 2)
			)
			(9
				(ego view: 19 loop: 2 cel: 0 setCycle: EndLoop self)
				(teleport play:)
			)
			(10
				(spider setCycle: 0)
				(baba
					setLoop: 8
					xStep: 2
					yStep: 5
					setCycle: Walk
					setMotion: PolyPath (+ (ego x?) 20) (- (ego y?) 5) self
				)
			)
			(11 (= cycles 2))
			(12
				(= babaSpeaks TRUE)
				(messager say: N_ROOM NULL C_STAY_THERE 1 self)
			)
			(13
				(= babaSpeaks FALSE)
				(baba setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			(14
				(= babaSpeaks TRUE)
				(messager say: N_ROOM NULL C_FROG_AGAIN 1 self)
			)
			(15
				(messager say: N_ROOM NULL C_BABA_2ND_TIME 2 self)
			)
			(16
				(bat setLoop: 5 setCycle: Forward)
				(spider setCycle: Forward)
				(= seconds 2)
			)
			(17
				(= babaState babaBRING)
				(bat setCycle: 0)
				(spider setCycle: 0)
				(messager say: N_ROOM NULL C_BABA_2ND_TIME 3 self)
			)
			(18
				(= babaSpeaks FALSE)
				(self cue:)
			)
			(19
				(= babaSpeaks TRUE)
				(messager say: N_ROOM NULL C_YES_NAME 2 self)
			)
			(20
				(messager say: N_ROOM NULL C_BABA_2ND_TIME 4 self)
			)
			(21 (self cue:))
			(22
				(messager say: N_ROOM NULL C_BABA_2ND_TIME 5 self)
			)
			(23
				(bat setCycle: Forward)
				(spider setCycle: Forward)
				(= seconds 3)
			)
			(24
				(bat setCycle: 0)
				(spider setCycle: 0)
				(messager say: N_ROOM NULL C_BABA_2ND_TIME 6 self)
			)
			(25 (self cue:))
			(26
				(messager say: N_ROOM NULL C_BABA_2ND_TIME 7 self)
			)
			(27
				(= babaSpeaks FALSE)
				(baba setCycle: EndLoop self)
			)
			(28
				(teleport play:)
				(ego setCycle: BegLoop self)
			)
			(29
				(NormalEgo)
				(ego loop: 0)
				(= babaSpeaks TRUE)
				(messager say: N_ROOM NULL C_BABA_2ND_TIME 8 self)
			)
			(30
				(if (ego has: iMandrake)
					(SolvePuzzle f21GiveRoot 3))
				(= babaSpeaks FALSE)
				(= cycles 5)
			)
			(31
				(if (ego has: iMandrake)
					(baba setPri: 9)
					(= babaSpeaks TRUE)
					(messager say: N_ROOM NULL C_BABA_GOT_ROOT 1 self)
					(Bclr fBabaCurse)
				else
					(rm21 setScript: noBringDeath)
				)
			)
			(32
				(bat setCycle: Forward)
				(spider setCycle: Forward)
				(messager say: N_ROOM NULL C_AFTER_ROOT 9 self)
			)
			(33
				(ego use: iMandrake)
				(= babaSpeaks FALSE)
				(= seconds 4)
			)
			(34
				(bat setCycle: 0)
				(spider setCycle: 0)
				(self cue:)
			)
			(35
				(= babaSpeaks TRUE)
				(messager say: N_ROOM NULL C_AFTER_ROOT 10 self)
			)
			(36
				(messager say: N_ROOM NULL C_AFTER_ROOT 11 self)
			)
			(37
				(bat setCycle: Forward)
				(spider setCycle: Forward)
				(= seconds 4)
			)
			(38
				(messager say: N_ROOM NULL C_AFTER_ROOT 12 self)
			)
			(39
				(messager say: N_ROOM NULL C_AFTER_ROOT 13 self)
			)
			(40 (self cue:))
			(41
				(= babaSpeaks FALSE)
				(baba setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			(42
				(messager say: N_ROOM 0 12 14 self)
			)
			(43
				(TP
					posn: (ego x?) (ego y?)
					setPri: 15
					init:
					setCycle: CycleTo 6 1 self
				)
				(teleport play:)
			)
			(44
				(ego hide:)
				(TP setCycle: EndLoop self)
			)
			(45
				(client setScript: 0)
				(curRoom newRoom: 22)
			)
		)
	)
)

(instance bubbleMusic of Sound
	(properties
		flags $ffff
		number 51
		priority 5
		loop -1
	)
)

(instance teleport of Sound
	(properties
		priority 15
	)
)

(instance babaTalker of Talker
	(properties
		x 10
		y 10
		view 1021
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2021)
		(PalVary PALVARYTARGET 2021)
		(AssertPalette 1021)
		(= font userFont)
		(super
			init: babaTalkerBust babaTalkerMouth babaTalkerEyes &rest
		)
	)
)

(instance babaTalkerBust of Prop
	(properties
		view 1021
	)
)

(instance babaTalkerMouth of Prop
	(properties
		nsTop 48
		nsLeft 41
		view 1021
		loop 1
	)
)

(instance babaTalkerEyes of Prop
	(properties
		nsTop 29
		nsLeft 46
		view 1021
		loop 2
	)
)
