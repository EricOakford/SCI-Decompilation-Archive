;;; Sierra Script 1.0 - (do not remove this comment)
(script# ENDGAME2)
(include game.sh) (include "602.shm")
(use Main)
(use Procs)
(use LoadMany)
(use Window)
(use Path)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	EndGame2 0
)

(local
	local0
	[upPts 29] = [159 90 154 86 158 85 162 83 174 82 184 84 193 86 206 88 219 90 233 85 248 81 264 78 283 74 295 68 -32768]
	[waterPts 15] = [274 94 261 100 245 108 238 114 228 120 214 125 199 128 -32768]
	[awayPts 28] = [128 145 149 139 175 131 199 123 223 115 247 107 271 99 295 91 319 83 -32768 140 145 122 122 137 127 129 139]
	heroScriptTimer
	saveBits
	[local75 10] = [66 66 3 3 3 2 160 120]
)
(procedure (NextScript)
	(if modelessDialog
		(modelessDialog dispose:)
	)
	(cast eachElementDo: #dispose 81)
	(switch (curRoom script?)
		(awardScript
			(curRoom setScript: heroScript)
		)
		(heroScript
			(curRoom setScript: bigScript)
		)
		(bigScript
			(SaveHero)
		)
	)
)

(procedure (SaveHero)
	(theGame setCursor: normalCursor)
	(DisposeScript PATH)
	(curRoom newRoom: CHARSAVE)
)

(instance creditWindow of SysWindow
	(properties)
)

(instance hairL of Prop
	(properties
		x 126
		y 42
		view 148
	)
)

(instance hairR of Prop
	(properties
		x 124
		y 99
		view 148
		loop 3
	)
)

(instance hairM of Prop
	(properties
		x 81
		y 151
		view 148
		loop 1
		signal (| fixedLoop ignrAct)
	)
)

(instance cape of Prop
	(properties
		x 223
		y 160
		view 148
		loop 2
		signal (| fixedLoop ignrAct)
	)
)

(instance bck of Actor
	(properties
		x -48
		y 182
		view 101
		loop 8
		signal (| fixPriOn fixedLoop ignrAct)
	)
)

(instance mid of Actor
	(properties
		x -24
		y 182
		view 101
		loop 8
		cel 1
		signal (| fixedLoop ignrAct)
	)
)

(instance frt of Actor
	(properties
		x -2
		y 180
		view 101
		loop 8
		cel 2
		signal (| fixPriOn fixedLoop ignrAct)
	)
)

(instance carpet of Actor
	(properties)
)

(instance upPath of Path
	(properties)
	
	(method (at n)
		(return [upPts n])
	)
)

(instance waterTurn of Path
	(properties)
	
	(method (at n)
		(return [waterPts n])
	)
)

(instance awayPath of Path
	(properties)
	
	(method (at n)
		(return [awayPts n])
	)
)

(instance bigCarpet of Actor
	(properties)
)

(instance claw1 of Prop
	(properties
		x 51
		y 92
		view 906
		priority 3
	)
)

(instance claw2 of Prop
	(properties
		x 127
		y 87
		view 906
		loop 1
		cel 1
		priority 3
	)
)

(instance head of Prop
	(properties
		x 87
		y 49
		view 906
		loop 2
	)
)

(instance flame of Actor
	(properties
		view 907
	)
)

(instance EndGame2 of Room
	(properties
		picture 39
		style VSHUTTER
	)
	
	(method (init)
		(Load SOUND (SoundFX 99))
		(LoadMany PICTURE 148 101 750)
		(LoadMany VIEW 139 101 148 906 907)
		(super init: &rest)
		(SolvePuzzle f600EndGame 25)
		(DoSound SoundOn TRUE)
		(theGame setSpeed: 6)
		(HandsOff)
		(cond 
			((Btst fSavedElsa)
				((inventory at: iSilver)
					amount: (+ ((inventory at: iSilver) amount?) 1100)
				)
			)
			((Btst fFlag309)
				((inventory at: iSilver)
					amount: (+ ((inventory at: iSilver) amount?) 600)
				)
			)
		)
		(if (Btst fFlag310)
			((inventory at: iSilver)
				amount: (+ ((inventory at: iSilver) amount?) 300)
			)
		)
		(self setScript: awardScript)
	)
	
	(method (dispose)
		(StatusLine disable:)
		(super dispose:)
	)
)

(instance awardScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (NextScript))
		)
	)
)

(instance post1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(if (and (Btst fBabaFrog) (Btst fSavedBarnard))
					(messager say: N_ROOM NULL C_BESTENDING)
				else
					(= seconds 5)
				)
			)
		)
	)
)

(instance post2 of Script
	(properties)
	
	(method (dispose)
		(if (and (>= state 1) modelessDialog)
			(modelessDialog dispose:)
		)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(if (Btst fBabaFrog)
					(= saveBits (messager say: N_ROOM NULL C_BABAFROG))
				else
					(= saveBits (messager say: N_ROOM NULL C_OFFTOSHAPEIR))
				)
				(= seconds 9)
			)
			(2
				(if (Btst fBabaFrog)
					(messager say: N_ROOM NULL C_OFFTOSHAPEIR)
				else
					(messager say: N_ROOM NULL C_STILLCURSED)
				)
				(= seconds 8)
			)
			(3 (self dispose:))
		)
	)
)

(instance heroScript of Script
	(properties)
	
	(method (init)
		(hairL setCycle: Forward cycleSpeed: 6)
		(hairM setCycle: Forward cycleSpeed: 6)
		(cape setCycle: Forward cycleSpeed: 6)
		(hairL setPri: 5 init:)
		(hairR setPri: 5 init:)
		(hairM setPri: 5 init:)
		(cape setPri: 2 init:)
		(= heroScriptTimer 55)
		(super init: &rest)
		(StatusLine disable:)
		(curRoom style: 0 drawPic: 148)
		(self setScript: post1)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= local0 0) (= ticks 5))
			(1
				(if heroScriptTimer
					(-- heroScriptTimer)
					(self changeState: 0)
				else
					(NextScript)
				)
			)
		)
	)
)

(instance bigScript of Script
	(properties)
	
	(method (init &tmp temp0 temp1)
		(bck init:)
		(mid init:)
		(frt init:)
		(= temp0
			(switch howFast
				(0 4)
				(1 3)
				(else  2)
			))
		(= temp1
			(switch howFast
				(0 2)
				(else  1)
			))
		(bck setStep: temp0 temp1)
		(mid setStep: temp0 temp1)
		(frt setStep: temp0 temp1)
		(super init: &rest)
		(curRoom style: IRISOUT drawPic: 750)
		(self setScript: post2)
	)
	
	(method (doit &tmp temp0)
		(frt
			priority: (- (mid priority?) 1)
			posn: (+ (mid x?) 22) (- (mid y?) 2)
		)
		(bck
			priority: (- (mid priority?) 1)
			posn: (- (mid x?) 24) (mid y?)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mid setMotion: MoveTo 373 22 self)
			)
			(1 (NextScript))
		)
	)
)

(instance flyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom style: VSHUTTER drawPic: 101)
				(carpet
					view: 101
					setLoop: 0
					setCel: 0
					setStep: 1 1
					posn: 168 95
					ignoreActors: TRUE
					init:
					setScript: cornyCredits
					setMotion: upPath self
				)
			)
			(1 (self changeState: 29))
			(2
				(carpet cel: 1 setPri: 8 setMotion: MoveTo 312 72 self)
			)
			(3
				(carpet cel: 2 setMotion: MoveTo 301 82 self)
			)
			(4
				(carpet cel: 3 setMotion: MoveTo 292 86 self)
			)
			(5
				(carpet setMotion: waterTurn self)
			)
			(6
				(carpet setMotion: MoveTo 184 133 self)
			)
			(7
				(carpet setMotion: MoveTo 170 137 self)
			)
			(8
				(carpet cel: 4 setMotion: MoveTo 150 135 self)
			)
			(9
				(carpet cel: 5 setMotion: MoveTo 143 126 self)
			)
			(10
				(carpet cel: 6 setMotion: MoveTo 153 123 self)
			)
			(11
				(carpet setMotion: MoveTo 164 120 self)
			)
			(12
				(carpet cel: 7 setMotion: MoveTo 170 116 self)
			)
			(13
				(carpet cel: 8 setMotion: MoveTo 186 118 self)
			)
			(14
				(carpet cel: 9 setMotion: MoveTo 197 122 self)
			)
			(15
				(carpet cel: 10 setMotion: MoveTo 187 128 self)
			)
			(16
				(carpet cel: 11 setMotion: MoveTo 171 140 self)
			)
			(17
				(carpet cel: 12 setMotion: MoveTo 166 143 self)
			)
			(18
				(carpet cel: 13 setMotion: MoveTo 161 146 self)
			)
			(19
				(carpet cel: 14 setMotion: MoveTo 156 149 self)
			)
			(20
				(carpet cel: 3 setMotion: MoveTo 114 149 self)
			)
			(21
				(carpet setLoop: 2 cel: 0 setMotion: MoveTo 90 145 self)
			)
			(22
				(carpet setMotion: MoveTo 72 130 self)
			)
			(23
				(carpet setPri: 8 setMotion: MoveTo 61 122 self)
			)
			(24
				(carpet cel: 1 setMotion: MoveTo 64 135 self)
			)
			(25
				(carpet setMotion: MoveTo 75 141 self)
			)
			(26
				(carpet setMotion: MoveTo 85 143 self)
			)
			(27
				(carpet cel: 2 setMotion: MoveTo 95 145 self)
			)
			(28
				(carpet
					setLoop: 3
					cel: 0
					cycleSpeed: 16
					setCycle: Forward
					setMotion: awayPath self
				)
			)
			(29 (NextScript))
		)
	)
)

(instance cornyCredits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 10)
			)
			(1
				(messager say: N_ROOM NULL C_LORICOLE 1 self)
			)
			(2
				(messager say: N_ROOM NULL C_KENNNISHIUYE 1 self)
			)
			(3
				(messager say: N_ROOM NULL C_JEFFCROWE 1 self)
			)
			(4
				(messager say: N_ROOM NULL C_JERRYMOORE 1 self)
			)
			(5
				(messager say: N_ROOM NULL C_BOBFISCHBACH 1 self)
			)
			(6
				(messager say: N_ROOM NULL C_LARRYSCOTT 1 self)
			)
			(7
				(messager say: N_ROOM NULL C_JERRYSHAW 1 self)
			)
			(8
				(messager say: N_ROOM NULL C_COREYCOLE 1 self)
			)
			(9
				(messager say: N_ROOM NULL C_MARKSEIBERT 1 self)
			)
			(10
				(messager say: N_ROOM NULL C_CINDYWALKER 1 self)
			)
			(11
				(messager say: N_ROOM NULL C_GURUKA 1 self)
				(= seconds 6)
			)
			(12
				(messager say: N_ROOM NULL C_PROMOTIONALCONSIDERATIONS 1 self)
			)
		)
	)
)
