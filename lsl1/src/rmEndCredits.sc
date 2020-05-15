;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmEndCredits) ;375
(include game.sh)
(use Main)
(use LLRoom)
(use LoadMany)
(use Sound)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm375 0
)

(local
	local0
	local1
	[printRect 4]
	local6
	fireworkX
	fireworkY
)
(procedure (Measure theString)
	(TextSize @printRect theString 0 315)
	(return (/ (- 180 (- [printRect 2] [printRect 0])) 2))
)

(procedure (RollCredits theString &tmp theY theColor)
	(curRoom drawPic: 376 SCROLLUP)
	(= theY (Measure theString))
	(= theColor
		[myTextColor8 (- (Random 130 147) 130)]
	)
	(Display theString
		p_color theColor
		p_at 1 theY
		p_width 318
		p_font bigFont
		p_mode teJustCenter
	)
)

(procedure (DIsposeFireworks)
	(globalSound stop:)
	(soundFx stop:)
	(sfxFWExplode stop:)
	(sfxFWLaunch stop:)
	(firework1 dispose:)
	(firework2 dispose:)
	(firework3 dispose:)
	(firework4 dispose:)
	(firework5 dispose:)
	(firework6 dispose:)
)

(instance rm375 of LLRoom
	(properties)
	
	(method (init &tmp theJumpTo)
		(= theJumpTo JumpTo)
		(theGame setCursor: theCursor TRUE 310 190)
		(if (Btst 46)
			(curRoom drawPic: 720)
			(curRoom setScript: sCredits)
		else
			(curRoom drawPic: 376)
			(globalSound number: 374 loop: 1 flags: mNOPAUSE)
			(soundFx number: 374 loop: 1 flags: mNOPAUSE)
			(LoadMany SOUND 373 374 700)
			(HandsOff)
			(curRoom setScript: sEcstasy)
			(ego init: x: 100 y: 1000 stopUpd:)
		)
		(super init:)
		(theGame setCursor: theCursor TRUE 310 190)
	)
)

(instance sEcstasy of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			((== local1 2) (= local1 0) (Palette PALLoad 376 2))
			(local1 (Palette PALCycle 64 190 1))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(= fireworkX (Random 40 280))
				(= fireworkY (Random 20 100))
				(= register (Random 140 180))
				(sfxFWLaunch play:)
				(launchFirework init: x: register setCel: 0 setCycle: EndLoop)
				(sparky
					init:
					show:
					x: register
					y: 185
					moveSpeed: (+ 1 howFast)
					setMotion: sparkJump fireworkX fireworkY self
				)
			)
			(2
				(sparky hide:)
				(switch local6
					(0
						(firework1
							cycleSpeed: (* 3 howFast)
							init:
							x: fireworkX
							y: fireworkY
							setCel: 0
							setCycle: EndLoop
						)
						(sfxFWExplode play:)
					)
					(1
						(firework2
							cycleSpeed: (* 3 howFast)
							init:
							x: fireworkX
							y: fireworkY
							setCel: 0
							setCycle: EndLoop
						)
						(globalSound play:)
					)
					(2
						(firework3
							cycleSpeed: (* 3 howFast)
							init:
							x: fireworkX
							y: fireworkY
							setCel: 0
							setCycle: EndLoop
						)
						(soundFx play:)
					)
					(3
						(firework4
							cycleSpeed: (* 3 howFast)
							init:
							x: fireworkX
							y: fireworkY
							setCel: 0
							setCycle: EndLoop
						)
						(sfxFWExplode play:)
					)
					(4
						(firework5
							cycleSpeed: (* 3 howFast)
							init:
							x: fireworkX
							y: fireworkY
							setCel: 0
							setCycle: EndLoop
						)
						(globalSound play:)
					)
					(5
						(firework6
							cycleSpeed: (* 3 howFast)
							init:
							x: fireworkX
							y: fireworkY
							setCel: 0
							setCycle: EndLoop
						)
						(soundFx play:)
					)
				)
				(= cycles (Random 1 4))
			)
			(3
				(= local1 1)
				(if (< local6 6) (++ local6) else (= local6 0))
				(= cycles 1)
			)
			(4
				(if (> local0 30)
					(= local1 2)
					(= cycles 1)
				else
					(++ local0)
					(self start: 1 init:)
				)
			)
			(5
				(sfxFWExplode dispose:)
				(= seconds 5)
			)
			(6
				(DIsposeFireworks)
				(self dispose:)
				(curRoom newRoom: 370)
			)
		)
	)
)

(instance sCredits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(RollCredits {Executive Producer\nKen Williams})
				(= seconds 3)
			)
			(2
				(RollCredits {Creative Director\nBill Davis})
				(= seconds 3)
			)
			(3
				(RollCredits {Director\nAl Lowe})
				(= seconds 3)
			)
			(4
				(RollCredits {Producer\nStuart Moulder})
				(= seconds 3)
			)
			(5
				(RollCredits {Game Designer\nAl Lowe})
				(= seconds 3)
			)
			(6
				(RollCredits {Art Designer\nWilliam Skirvin})
				(= seconds 3)
			)
			(7
				(RollCredits {Lead Programmer\nOliver Brelsford})
				(= seconds 3)
			)
			(8
				(RollCredits {Composer\nChris Braymen})
				(= seconds 3)
			)
			(9
				(RollCredits
					{Animators\nBarry Smith\nJennifer Shontz\nRoger Hardy Jr.\nWilliam D. Skirvin\nEric Apel\nRuss Truelove\nDesie Hartman}
				)
				(= seconds 3)
			)
			(10
				(RollCredits
					{Background Artists\nWilliam D. Skirvin\nJay Allan Friedmann\nJennifer Shontz\nJane Cardinal\nMaurice Morgan}
				)
				(= seconds 3)
			)
			(11
				(RollCredits
					{Programmers\nOliver Brelsford\nMike Larsen\nAl Lowe}
				)
				(= seconds 3)
			)
			(12
				(RollCredits
					{Witty Text and Documentation\nJosh Mandel}
				)
				(= seconds 3)
			)
			(13
				(RollCredits
					{Music Director\nMark Seibert\n\nTheme Song Composed by\nAl Lowe\n\nSound Effects\nChris Braymen\nMark Seibert\nand Orpheus Hanley}
				)
				(= seconds 3)
			)
			(14
				(RollCredits
					{System Programmers\nJ. Mark Hood\nJeff Stephenson\nBob Heitman\nPablo Ghenis\nDan Foy\nLarry Scott\nMark Wilden\nEric Hart\nChris Smith}
				)
				(= seconds 3)
			)
			(15
				(RollCredits
					{Quality Assurance Team Leaders\nMike Pickhinke and Keri Cooper}
				)
				(= seconds 3)
			)
			(16
				(curRoom drawPic: 376 SCROLLUP)
				(= seconds 20)
			)
			(17
				(globalSound fade: 0 15 15 1 self)
			)
			(18
				(curRoom drawPic: 376 SCROLLUP)
				(Display
					{It's over!\nQuit!!\nGet a Life!!!}
					p_color myTextColor7
					p_at 1 30
					p_width 318
					p_font giantFont
					p_mode teJustCenter
				)
				(= seconds 3)
			)
			(19
				(= quit TRUE)
			)
		)
	)
)

(instance sfxFWExplode of Sound
	(properties
		flags mNOPAUSE
		number 374
	)
)

(instance sfxFWLaunch of Sound
	(properties
		flags mNOPAUSE
		number 373
	)
)

(instance sparkJump of JumpTo
	(properties)
	
	(method (doit)
		(if (>= (++ b-moveCnt) (client moveSpeed?))
			(= b-moveCnt 0)
			(super doit:)
		)
	)
)

(instance sparky of Actor
	(properties
		y 185
		yStep 5
		view 375
		signal (| ignrAct fixedLoop fixedCel)
		xStep 4
		moveSpeed 5
	)
)

(instance launchFirework of Prop
	(properties
		x 150
		y 189
		view 375
		signal (| ignrAct ignrHrz)
	)
)

(instance firework1 of Prop
	(properties
		x 61
		y 57
		view 375
		loop 1
		signal (| ignrAct ignrHrz)
	)
)

(instance firework2 of Prop
	(properties
		x 61
		y 57
		view 375
		loop 2
		signal (| ignrAct ignrHrz)
	)
)

(instance firework3 of Prop
	(properties
		x 61
		y 57
		view 375
		loop 3
		signal (| ignrAct ignrHrz)
	)
)

(instance firework4 of Prop
	(properties
		x 61
		y 57
		view 375
		loop 4
		signal (| ignrAct ignrHrz)
	)
)

(instance firework5 of Prop
	(properties
		x 61
		y 57
		view 375
		loop 5
		signal (| ignrAct ignrHrz)
	)
)

(instance firework6 of Prop
	(properties
		x 61
		y 57
		view 375
		loop 6
		signal (| ignrAct ignrHrz)
	)
)
