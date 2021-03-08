;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1040)
(include game.sh) (include "1040.shm")
(use Main)
(use AnimDialog)
(use SpeakWindow)
(use Talker)
(use Scaler)
(use Osc)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm1040 0
	tkrRoger 5
	tkrFlo 13
)

(instance rm1040 of Room
	(properties
		picture 128
		style (| BLACKOUT FADEOUT)
	)
	
	(method (init)
		(LoadMany RES_VIEW 688 696 697 687 0)
		(NormalEgo 0)
		(switch prevRoomNum
			(1020
				(curRoom setScript: sRogCaught)
			)
			(1045
				(curRoom setScript: sFollowWD40)
			)
			(1050
				(curRoom setScript: sBlobOutside)
			)
			(else 
				(curRoom setScript: sGetAway)
			)
		)
		(super init:)
		(theMusic2 number: 654 setLoop: -1 play:)
	)
)

(instance sGetAway of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(rogHead x: 3 y: 63 init:)
				(droole init: setCycle: Oscillate)
				(flo init: setCycle: Oscillate)
				(= seconds 3)
			)
			(1
				(if (Btst fSetSelfDestruct)
					(messager say: N_SELF_DESTRUCT NULL NULL 0 self)
				else
					(++ state)
					(= cycles 1)
				)
			)
			(2
				(curRoom newRoom: 1060)
				(self dispose:)
			)
			(3 (messager say: N_GET_AWAY NULL NULL 0 self))
			(4
				(blob
					view: 697
					loop: 2
					cel: 0
					x: 280
					y: 71
					init:
					setCycle: CycleTo 7 1 self
				)
				(exAct1
					view: 697
					loop: 1
					cel: 0
					x: 199
					y: 72
					init:
					setCycle: CycleTo 7
				)
			)
			(5
				(blob setCycle: EndLoop self)
				(exAct1 setCycle: EndLoop)
				(ooze3 init: setCycle: EndLoop)
			)
			(6
				(ShakeScreen 5 shakeSRight)
				(= seconds 2)
			)
			(7
				(PalVary PALVARYSTART 2100 1)
				(= seconds 2)
			)
			(8 (EgoDead 44))
		)
	)
)

(instance sRogCaught of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(pukoid1 init:)
				(pukoid2 init:)
				(pukoid3 init:)
				(pukoid4 init:)
				(pukoid5 init:)
				(pukoid6 init:)
				(pukoid7 init:)
				(quirk init:)
				(quirkArm init:)
				(quirkPuke init:)
				(= seconds 2)
			)
			(1 (pukoid1 setCycle: EndLoop self))
			(2
				(pukoid1 setLoop: 1 setCel: 0 setCycle: CycleTo 4 1 self)
			)
			(3
				(quirkPuke setCycle: EndLoop)
				(ego
					view: 0
					setLoop: -1
					setLoop: 8
					setScale: 0
					cel: 2
					x: 176
					y: 148
					setCycle: 0
					init:
				)
				(pukoid1 setCycle: EndLoop self)
			)
			(4 (messager say: N_ROGER_CAUGHT NULL NULL 1 self))
			(5 (messager say: N_ROGER_CAUGHT NULL NULL 2 self))
			(6
				(quirk setCel: 1)
				(quirkArm loop: 2 x: 140 y: 162)
				(quirkPuke
					loop: 1
					cel: 0
					x: 171
					y: 158
					setCycle: EndLoop self
				)
			)
			(7 (messager say: N_ROGER_CAUGHT NULL NULL 3 self))
			(8 (messager say: N_ROGER_CAUGHT NULL NULL 4 self))
			(9
				(if (not (Btst fGotNitroTank))
					(messager say: N_FORGOT_NITRO NULL NULL 0 self)
				else
					(theMusic2 number: 260 setLoop: 1 play:)
					(wd40 init: setCycle: EndLoop self)
				)
			)
			(10
				(if (not (Btst fGotNitroTank))
					(EgoDead deathFORGOTNITRO)
				else
					(theMusic1 number: 35 setLoop: -1 play:)
					(pukoid1 loop: 2 cel: 0 setCycle: CycleTo 2 1)
					(pukoid2 setCycle: CycleTo 2 1)
					(pukoid3 setCycle: CycleTo 2 1)
					(pukoid4 setCycle: CycleTo 2 1)
					(pukoid5 setCycle: CycleTo 2 1)
					(pukoid6 setCycle: CycleTo 2 1)
					(pukoid7 setCycle: CycleTo 2 1)
					(ego view: 688 setLoop: -1 setLoop: 3 setCycle: EndLoop self)
				)
			)
			(11 (curRoom newRoom: 1045))
		)
	)
)

(instance sBlobOutside of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(blob init: setCycle: Oscillate)
				(= seconds 3)
			)
			(1
				(theMusic2 number: 103 setLoop: 1 play:)
				(= seconds 2)
			)
			(2
				(theMusic2 number: 654 setLoop: -1 play:)
				(ego init: hide:)
				(rogHead
					init:
					setCycle: 0
					setLoop: -1
					setLoop: 4
					setMotion: MoveTo 3 63 self
				)
			)
			(3 (= seconds 2))
			(4
				(theMusic2 number: 603 setLoop: 1 play: self)
			)
			(5
				(theMusic2 number: 654 setLoop: -1 play:)
				(messager say: N_FLO_CALLS NULL NULL 0 self)
			)
			(6
				(shuttle
					init:
					setLoop: 1
					setCycle: 0
					setScale: Scaler 100 48 120 99
					setMotion: MoveTo 189 109 self
				)
			)
			(7 (messager say: N_BLOB_OUTSIDE NULL NULL 1 self))
			(8
				(shuttle setMotion: MoveTo 205 102 self)
			)
			(9 (messager say: N_BLOB_OUTSIDE NULL NULL 2 self))
			(10
				(shuttle setMotion: MoveTo 211 99 self)
			)
			(11
				(exAct1 init: setCycle: EndLoop self)
			)
			(12
				(shuttle dispose:)
				(blob loop: 3 cel: 0 x: 220 y: 97 setCycle: Oscillate 3 self)
			)
			(13
				(exAct1
					view: 696
					loop: 4
					cel: 0
					x: 225
					y: 87
					setCycle: EndLoop self
				)
			)
			(14
				(messager say: N_BLOB_OUTSIDE NULL NULL 3 self)
			)
			(15
				(tkrFlo
					normal: 0
					keepWindow: TRUE
					textY: 85
					curNoun: 5
					curVerb: NULL
					curCase: 1
				)
				(= cycles 1)
			)
			(16
				(theGame handsOn:)
				(theIconBar select: (theIconBar at: ICON_DO))
				(theGame setCursor: ARROW_CURSOR)
				(messager say: N_FLO NULL C_FLO_COMMAND 0 self)
			)
			(17
				(theGame handsOff:)
				(tkrFlo normal: 1 textY: 10)
				(if (not (tkrFlo whichSelect?))
					(= state (- state 3))
					(= cycles 1)
				else
					(switch (tkrFlo whichSelect?)
						(1 (messager say: N_FLO NULL C_FLO_COMMAND 0 self))
						(2 (messager say: N_FLO NULL C_FLO_COMMAND 0 self))
						(3 (messager say: N_FLO NULL C_FLO_COMMAND 0 self))
					)
				)
			)
			(18 (exAct1 setCycle: BegLoop self))
			(19
				(exAct1 hide:)
				(if (== (tkrFlo whichSelect?) 1)
					(theMusic1 fade: 0 10 10 1)
					(SolvePuzzle f1040CallCliffy 20)
					(curRoom newRoom: 240)
				else
					(blob setMotion: MoveTo 225 49 self)
				)
			)
			(20
				(theMusic2 number: 250 setLoop: 1 play:)
				(ShakeScreen 5 shakeSRight)
				(= seconds 2)
			)
			(21
				(blob
					view: 697
					loop: 2
					cel: 0
					x: 280
					y: 71
					setCycle: CycleTo 7 1 self
				)
				(exAct1
					view: 697
					loop: 1
					cel: 0
					x: 199
					y: 72
					show:
					setCycle: CycleTo 7
				)
			)
			(22
				(blob setCycle: EndLoop self)
				(exAct1 setCycle: EndLoop)
				(ooze3 init: setCycle: EndLoop)
			)
			(23
				(PalVary PALVARYSTART 2100 1)
				(= seconds 4)
			)
			(24
				(if (== (tkrFlo whichSelect?) 2)
					(EgoDead deathPHASERONBLOB)
				else
					(EgoDead deathDEFEATIST)
				)
			)
		)
	)
)

(instance sFollowWD40 of Script
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(< (wd40 x?) 50)
				(== (ego mover?) 0)
				(== state 3)
			)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 688
					cel: 0
					x: 176
					y: 148
					setLoop: -1
					setLoop: 4
					init:
					setCycle: EndLoop self
				)
				(wd40 view: 688 loop: 1 cel: 0 x: 151 y: 147 init:)
				(wd40head init: setCycle: Forward)
				(pukoid1 loop: 2 setCel: (pukoid1 lastCel:) init:)
				(pukoid2 setCel: (pukoid2 lastCel:) init:)
				(pukoid3 setCel: (pukoid3 lastCel:) init:)
				(pukoid4 setCel: (pukoid4 lastCel:) init:)
				(pukoid5 setCel: (pukoid5 lastCel:) init:)
				(pukoid6 setCel: (pukoid6 lastCel:) init:)
				(pukoid7 setCel: (pukoid7 lastCel:) init:)
				(cloud1 init: setCycle: Forward)
				(cloud2 init: setCycle: Forward)
				(cloud3 init: setCycle: Forward)
				(quirk cel: 1 init:)
				(quirkArm loop: 2 x: 140 y: 162 init:)
			)
			(1 (messager say: N_FOLLOW_WD40 NULL NULL 0 self))
			(2
				(wd40 loop: 6 cel: 0 setCycle: EndLoop self)
				(wd40head dispose:)
				(ego setLoop: -1 setLoop: 7 cel: 0 setCycle: EndLoop)
				(theMusic2 fade: 0 10 10 1)
			)
			(3
				(curRoom newRoom: 1050)
				(self dispose:)
			)
		)
	)
)

(instance quirkPuke of Prop
	(properties
		x 158
		y 157
		view 686
		loop 3
		signal ignrAct
	)
)

(instance wd40 of Actor
	(properties
		x 151
		y 147
		view 688
		signal ignrAct
	)
)

(instance pukoid1 of Actor
	(properties
		x 198
		y 148
		view 687
		signal ignrAct
	)
)

(instance pukoid2 of Actor
	(properties
		x 268
		y 175
		view 687
		loop 3
	)
)

(instance pukoid3 of Actor
	(properties
		x 67
		y 157
		view 687
		loop 7
	)
)

(instance pukoid4 of Actor
	(properties
		x 263
		y 132
		view 687
		loop 4
	)
)

(instance pukoid5 of Actor
	(properties
		x 157
		y 122
		view 687
		loop 5
		priority 1
		signal fixPriOn
	)
)

(instance pukoid6 of Actor
	(properties
		x 302
		y 147
		view 687
		loop 6
	)
)

(instance pukoid7 of Actor
	(properties
		x 98
		y 141
		view 687
		loop 8
	)
)

(instance blob of Actor
	(properties
		x 228
		y 89
		view 696
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and (== (self view?) 696) (self mover?))
			(self scaleX: (+ (* (- 89 (self y?)) 2) 128))
			(self scaleY: (+ (* (- 89 (self y?)) 2) 128))
			(self scaleSignal: scalable)
		)
	)
)

(instance shuttle of Actor
	(properties
		x 166
		y 120
		view 696
		loop 1
		priority 1
		signal (| ignrAct fixPriOn)
	)
)

(instance exAct1 of Actor
	(properties
		x 213
		y 98
		view 696
		loop 2
		priority 4
		signal (| ignrAct fixPriOn)
	)
)

(instance ooze3 of Prop
	(properties
		x 94
		y 69
		view 697
	)
)

(instance flo of Prop
	(properties
		x 145
		y 102
		view 697
		loop 5
		priority 5
		signal (| ignrAct fixPriOn)
	)
)

(instance droole of Prop
	(properties
		x 193
		y 104
		view 697
		loop 6
		priority 5
		signal (| ignrAct fixPriOn)
	)
)

(instance wd40head of Prop
	(properties
		x 149
		y 106
		view 688
		loop 2
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance cloud1 of Prop
	(properties
		x 242
		y 163
		view 689
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance cloud2 of Prop
	(properties
		x 25
		y 144
		view 689
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance cloud3 of Prop
	(properties
		x 190
		y 133
		view 689
		loop 2
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance tkrFlo of ChoiceTalker
	(properties
		x 175
		y 1
		view 1008
		talkWidth 85
		back 5
		textX -110
		textY 10
		normal 1
	)
	
	(method (init)
		(= font userFont)
		(if normal
			(self talkWidth: 85)
		else
			(self talkWidth: 200)
		)
		(super init: floBust floEyes floMouth &rest)
	)
	
	(method (dispose)
		(super dispose: &rest)
	)
)

(instance floBust of Prop
	(properties
		view 1008
	)
)

(instance floEyes of Prop
	(properties
		nsTop 33
		nsLeft 39
		view 1008
		loop 2
		signal ignrAct
	)
)

(instance floMouth of Prop
	(properties
		nsTop 43
		nsLeft 45
		view 1008
		loop 1
		signal ignrAct
	)
)

(instance tkrRoger of Talker
	(properties
		y 56
		view 697
		loop 4
		talkWidth 150
		textX 10
	)
	
	(method (init)
		(= font userFont)
		(= systemWindow SpeakWindow)
		(= talkWidth (WhichLanguage 220 220 220 220 150))
		(systemWindow
			tailX: 80
			tailY: 160
			xOffset: (WhichLanguage 105 105 105 105 65)
		)
		(super init: rogBust rogEyes rogMouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance rogBust of Prop
	(properties
		view 697
		loop 4
	)
)

(instance rogEyes of Prop
	(properties
		nsTop 46
		nsLeft 33
		view 697
		loop 8
	)
)

(instance rogMouth of Prop
	(properties
		nsTop 68
		nsLeft 2
		view 697
		loop 7
	)
)

(instance quirk of Actor
	(properties
		x 132
		y 120
		view 686
		loop 4
		priority 8
		signal (| ignrAct fixPriOn)
	)
)

(instance quirkArm of Actor
	(properties
		x 133
		y 162
		view 686
		signal ignrAct
	)
)

(instance rogHead of Actor
	(properties
		x -50
		y 63
		view 697
		loop 4
		priority 15
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_COMMUNICATOR
				(curRoom newRoom: 240)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
