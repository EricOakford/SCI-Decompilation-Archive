;;; Sierra Script 1.0 - (do not remove this comment)
(script# rHalfDome)
(include game.sh)
(use Main)
(use Print)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm100 0
)

(local
	logoTimer
)
(instance rm100 of Room

	(method (init)
		(Load PICTURE pCharSel)
		(LoadMany VIEW
			vSelFighter
			vSelWizard
			vSelThief
			vIntroDragon
			vIntroFlame2
			vQFGCooldown
			vHeroCooldown
			vIntroTitle
			vIntroFlame
		)
		(super init:)
		(if showSierraLogo
			(SetPort 0 0 200 320 0 0)
			(Palette PALIntensity 0 254 0)
			(= currentPic pHalfDome)
			(curRoom drawPic: pHalfDome FADEOUT)
			(glint init:)
			(glint2 init:)
			(self setScript: logoScript)
		else
			(self setScript: roomScript)
		)
	)
	
	(method (doit)
		(++ logoTimer)
		(if (and showSierraLogo (== (mod logoTimer 3) 0))
			(Palette PALCycle 95 218 -1)
		)
		(super doit:)
	)
)

(instance fighter of Prop
	(properties
		x 68
		y 129
		view vSelFighter
		cel 5
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 1
	)
)

(instance sparkle of Prop
	(properties
		x 162
		y 73
		view vHalfSparkle
		loop 1
		priority 13
		signal (| ignrAct fixPriOn)
		cycleSpeed 1
	)
)

(instance mage of Prop
	(properties
		x 158
		y 129
		view vSelWizard
		priority 12
		signal (| ignrAct fixPriOn)
		cycleSpeed 1
	)
)

(instance thief of Prop
	(properties
		x 248
		y 130
		view vSelThief
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 1
	)
)

(instance dragon of Prop
	(properties
		x 260
		y 101
		view vIntroDragon
		priority 6
		signal fixPriOn
		cycleSpeed 8
	)
)

(instance dragonFire of Prop
	(properties
		x 147
		y 74
		view vIntroFlame2
		priority 15
		signal fixPriOn
	)
)

(instance subTitle of Prop
	(properties
		x 172
		y 167
		view vQFGCooldown
	)
)

(instance qForGlory of Prop
	(properties
		x 56
		y 154
		view vHeroCooldown
	)
)

(instance glint of Prop
	(properties
		x 134
		y 34
		view vHalfSparkle
		cycleSpeed 2
	)
)

(instance glint2 of Prop
	(properties
		x 60
		y 155
		view vHalfSparkle
		loop 1
		cycleSpeed 2
	)
)

(instance fireSound of Sound
	(properties
		number sFire
	)
)

(instance logoScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 2)
			)
			(1
				(soundFx
					number: rHalfDome
					loop: 1
					flags: 0
					play: self
				)
			)
			(2
			)
			(3
				(glint setCycle: EndLoop self)
			)
			(4
				(glint dispose:)
			)
			(5
				(glint2 setCycle: EndLoop self)
			)
			(6
				(glint2 dispose:)
			)
			(7
				(= ticks 60)
			)
			(8
				(soundFx stop:)
				(= showSierraLogo FALSE)
				(SetPort 0 0 190 320 10 0)
				(curRoom setScript: roomScript)
				(self dispose:)
			)
		)
	)
)

(instance stamp of View
	(properties
		view vCharSel
	)
)

(instance swoosh of Sound
	(properties
		number sSwoosh
	)
)

(instance twinkle of Sound
	(properties
		number sTwinkle
	)
)

(instance roomScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= currentPic pIntro)
				(curRoom drawPic: pIntro)
				(dragon init:)
				(subTitle init: hide:)
				(qForGlory init: hide:)
				(dragonFire init: hide:)
				(soundFx
					priority: 100
					number: sShortIntro
					loop: 1
					flags: 0
					play: self
				)
				(= seconds 3)
			)
			(1
				(dragon setCel: 1)
				(= seconds 1)
			)
			(2
				(dragon setCycle: EndLoop self)
			)
			(3
				(dragon stopUpd:)
				(fireSound play:)
				(dragonFire show: setCycle: EndLoop self)
			)
			(4
				(qForGlory show: setCel: 0 setCycle: EndLoop self)
				(fireSound stop:)
				(dragonFire hide:)
			)
			(5
				(qForGlory view: vIntroTitle setLoop: 0 setCel: 0)
				(dragon startUpd: setCel: 6)
				(= ticks 25)
			)
			(6
				(dragon setCel: 10)
				(fireSound play:)
				(dragonFire
					view: vIntroFlame
					posn: 170 144
					setCel: 0
					show:
					setCycle: EndLoop self
				)
			)
			(7
				(fireSound stop:)
				(dragonFire hide:)
				(subTitle show: setCel: 0 setCycle: EndLoop self)
			)
			(8
				(subTitle view: vIntroTitle setLoop: 1 setCel: 0)
			)
			(9
				(dragon dispose:)
				(dragonFire dispose:)
				(qForGlory dispose:)
				(subTitle dispose:)
				(= currentPic pCharSel)
				(curRoom drawPic: pCharSel FADEOUT)
				(fighter init: setCel: LASTCEL)
				(mage init:)
				(thief init:)
				(= cycles 2)
			)
			(10
				(stamp
					init:
					setLoop: 3
					setCel: 0
					posn: 10 138
					signal: ignrAct
				)
				(= seconds 4)
			)
			(11
				(stamp setCel: 0 setLoop: 1 posn: 18 146)
				(fighter setCycle: BegLoop self)
				(= cycles 2)
			)
			(12
				(swoosh play:)
			)
			(13
				(swoosh stop:)
				(= ticks 60)
			)
			(14
				(stamp
					setLoop: 1
					setCel: 1
					posn: 202 156
					signal: ignrAct
				)
				(mage setCycle: EndLoop self)
			)
			(15
				(= seconds 1)
			)
			(16
				(twinkle play:)
				(sparkle init: cycleSpeed: 1 setCycle: Forward)
				(= ticks 120)
			)
			(17
				(twinkle stop:)
				(sparkle dispose:)
				(stamp
					setLoop: 1
					setCel: 2
					posn: 201 146
					signal: ignrAct
				)
				(thief setCycle: EndLoop self)
			)
			(18
				(= ticks 120)
			)
			(19
				(stamp dispose:)
				(= ticks 10)
			)
			(20
				(Print
					addText: {Return to the World of\nQuest for Glory I.}
					modeless: TRUE
					x: 80
					y: 10
					init:
				)
				(= seconds 9)
			)
			(21
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(soundFx fade: 5 30 5 0)
				(= ticks 60)
			)
			(22
				(soundFx fade:)
				(= ticks 60)
			)
			(23
				(curRoom newRoom: rMainStreet)
			)
		)
	)
)
