;;; Sierra Script 1.0 - (do not remove this comment)
(script# DICE)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Count)
(use Sort)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	diceRm 0
)

;EO: This script was particularly hard to get compiled.
; Fortunately, it seems to work okay.

(local
	local0
)
(instance dieBox_a of View
	(properties
		view 39
		loop 2
	)
)

(instance rollDieScript_a of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					cel: (Random 0 (client lastCel:))
					PromptForDiskChange: 1
				)
				(client hide: select: 0)
			)
			(1 (= seconds 2))
			(2
				(client PromptForDiskChange: 0 setScript: 0)
			)
		)
	)
)

(class Die_a of Prop
	(properties
		signal $0000
		sel_327 0
		sel_328 1
		sel_329 0
		PromptForDiskChange 0
	)
	
	(method (init)
		(super init:)
		((= sel_329 (Clone dieBox_a))
			x: (self x?)
			y: (self y?)
			stopUpd:
			init:
		)
		(self select: sel_327)
		(self stopUpd:)
	)
	
	(method (sel_330)
		(if sel_327 (self setScript: (Clone rollDieScript_a)))
	)
	
	(method (select param1)
		(if
		(= sel_327 (if argc param1 else (not sel_327)))
			(sel_329 show:)
		else
			(sel_329 hide:)
		)
	)
)

(class DiceGame_a of List
	(properties
		sel_327 0
		view 0
		loop 0
	)
	
	(method (init param1 &tmp temp0 newDie)
		(if argc
			(= temp0 0)
			(while (<= temp0 (- argc 2))
				((= newDie (Die_a new:))
					posn: [param1 temp0] [param1 (++ temp0)]
					view: view
					loop: loop
					init:
					hide:
				)
				(newDie setCel: (Random 0 (newDie lastCel:)))
				(self add: newDie)
				(++ temp0)
			)
		)
		(super init:)
	)
	
	(method (dispose)
		(self release:)
		(super dispose:)
	)
	
	(method (cue)
		(self eachElementDo: #cue &rest)
	)
)

(class oldSaltDice_a of DiceGame_a
	(properties
		sel_332 0
		sel_333 0
	)
	
	(method (sel_334 &tmp temp0 temp1 theSel_333 theSel_333_2)
		(= sel_333 0)
		(Sort self reverseLT_a)
		(= temp0 (Count self diceCount_a (self at: 2)))
		(= theSel_333 (+ ((self at: 2) cel?) 1))
		(switch temp0
			(5
				(= sel_332 (+ 500 (* 10 theSel_333)))
			)
			(4
				(= sel_332 (+ 400 (* 10 theSel_333)))
			)
			(else 
				(= temp0 (Count self diceCount_a (self at: 1)))
				(= temp1 (Count self diceCount_a (self at: 3)))
				(= theSel_333 (+ ((self at: 1) cel?) 1))
				(= theSel_333_2 (+ ((self at: 3) cel?) 1))
				(switch temp0
					(3
						(if (== temp1 2)
							(= sel_332 (+ 300 (* 10 theSel_333) theSel_333_2))
						else
							(= sel_332 (+ 200 (* 10 theSel_333)))
						)
					)
					(2
						(cond 
							((== temp1 3) (= sel_332 (+ 300 (* 10 theSel_333_2) theSel_333)))
							((== temp1 2)
								(= sel_332 (+ 100 (* 10 theSel_333) theSel_333_2))
								(= sel_333 theSel_333)
							)
							(else (= sel_332 (* 10 theSel_333)) (= sel_333 theSel_333))
						)
					)
					(else 
						(cond 
							((== temp1 3) (= sel_332 (+ 200 (* 10 theSel_333_2))))
							((== temp1 2) (= sel_332 (* 10 theSel_333_2)) (= sel_333 theSel_333_2))
							(else (= sel_332 0))
						)
					)
				)
			)
		)
	)
)

(instance reverseLT_a of Code

	(method (doit param1 param2)
		(return (> (param1 cel?) (param2 cel?)))
	)
)

(instance diceCount_a of Code

	(method (doit param1 param2)
		(return (== (param1 cel?) (param2 cel?)))
	)
)

(instance diceRm of Room
	(properties
		picture 39
		style DISSOLVE
	)
	
	(method (init)
		(super init:)
		(LoadMany VIEW 39 239 439 539 139)
		(LoadMany SOUND
			(SoundFX 76)
			(SoundFX 77)
			(SoundFX 89)
		)
		(oldSaltDice_a
			view: 39
			loop: 0
			init: 30 20 50 20 70 20 90 20 110 20
		)
		(johnnyDice init: 210 20 230 20 250 20 270 20 290 20)
		(saltRArmP stopUpd: init:)
		(redTumble setCel: (redTumble lastCel:) init:)
		(redTable setCel: 0 init:)
		(greenTumble
			setCel: (greenTumble lastCel:)
			setPri: 9
			init:
		)
		(greenTable setCel: 0 setPri: 9 init:)
		(cigarSmoke init: hide:)
		(saltLArmP stopUpd: init:)
		(saltMouth setPri: 8 init: cel: 1)
		(saltEyes setPri: 9 init: cel: 1)
		(johnnyLArmP init:)
		(johnnyRArmP stopUpd: init:)
		(johnnyMouth setPri: 6 init: hide:)
		(johnnyEyes setPri: 7 init: cel: 1)
		(johnnyFingers init: hide:)
		(rumBottle init: stopUpd:)
		(addToPics
			add: johnnyPV oldSaltV
			eachElementDo: #init
			doit:
		)
		(oldSaltMoney init:)
		(johnnyMoney init:)
		(curRoom setScript: pickJohnnyDice)
		(globalSound stop:)
		(soundDice number: (SoundFX 76) init:)
		(if (= howFast fast)
			(saltLArmP setScript: smokeCigar)
			(saltEyes setScript: blinkerScript)
			(johnnyLArmP setScript: johnTwitch)
			(johnnyEyes setScript: (Clone blinkerScript))
		)
	)
	
	(method (dispose)
		(johnnyDice dispose:)
		(oldSaltDice_a dispose:)
		(soundDice dispose:)
		(super dispose:)
	)
)

(instance blinkerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 40 100)))
			(1
				(client cel: 0)
				(= cycles 2)
			)
			(2
				(client cel: 1)
				(= cycles 2)
			)
			(3 (self init:))
		)
	)
)

(instance smokeCigar of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 50))
			(1
				(= register 1)
				(saltLArmP setLoop: 5 setCycle: EndLoop self)
			)
			(2 (= cycles 10))
			(3
				(saltLArmP setCycle: BegLoop self)
			)
			(4
				(= register 0)
				(saltMouth loop: 9 cel: 0)
				(cigarSmoke show: setCycle: EndLoop self)
			)
			(5
				(saltMouth loop: 1 cel: 1 setCycle: 0)
				(cigarSmoke hide:)
				(self dispose:)
			)
		)
	)
)

(instance johnTwitch of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 50 100)))
			(1
				(cond 
					(local0 (self init:))
					((Random 0 1)
						(= register 1)
						(johnnyLArmP loop: 4 setCycle: ForwardCounter 3 self)
					)
					(else
						(= register 2)
						(johnnyFingers show: setCycle: ForwardCounter 3 self)
					)
				)
			)
			(2
				(switch register
					(1 (johnnyLArmP loop: 5 cel: 0))
					(2 (johnnyFingers hide:))
				)
				(= register 0)
				(self init:)
			)
		)
	)
)

(instance johnnyDice of oldSaltDice_a
	(properties
		view 39
		loop 1
	)
)

(instance oldSaltV of PicView
	(properties
		y 162
		x 49
		view 239
		priority 7
	)
)

(instance johnnyPV of PicView
	(properties
		y 176
		x 261
		view 39
		loop 3
		priority 5
	)
)

(instance oldSaltMoney of View
	(properties
		y 122
		x 139
		view 539
		loop 6
		cel 3
	)
)

(instance johnnyMoney of View
	(properties
		y 168
		x 198
		view 539
		loop 7
		cel 3
	)
)

(instance rumBottle of View
	(properties
		y 168
		x 150
		view 439
		loop 6
		priority 13
	)
)

(instance johnnyLArmP of Prop
	(properties
		y 93
		x 274
		view 39
		loop 5
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 stopUpd:)
	)
)

(instance johnnyRArmP of Prop
	(properties
		y 87
		x 227
		view 39
		loop 7
	)
	
	(method (init)
		(super init:)
		(self setPri: 8 stopUpd:)
	)
)

(instance saltLArmP of Prop
	(properties
		y 113
		x 98
		view 239
		loop 4
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 stopUpd:)
	)
)

(instance saltRArmP of Prop
	(properties
		y 95
		x 45
		view 239
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 8 stopUpd:)
	)
)

(instance redTumble of Prop
	(properties
		y 125
		x 120
		view 539
		loop 5
	)
	
	(method (init)
		(self stopUpd:)
		(super init:)
	)
)

(instance redTable of View
	(properties
		y 125
		x 120
		view 539
	)
	
	(method (init)
		(self stopUpd:)
		(super init:)
	)
)

(instance greenTumble of Prop
	(properties
		y 143
		x 213
		view 439
		loop 5
	)
	
	(method (init)
		(self stopUpd:)
		(super init:)
	)
)

(instance greenTable of View
	(properties
		y 143
		x 213
		view 439
	)
	
	(method (init)
		(self stopUpd:)
		(super init:)
	)
)

(instance cigarSmoke of Prop
	(properties
		y 82
		x 91
		view 239
		loop 7
	)
)

(instance johnnyFingers of Prop
	(properties
		y 144
		x 235
		view 39
		loop 8
	)
)

(instance saltMouth of Prop
	(properties
		y 79
		x 85
		view 139
		loop 1
	)
)

(instance saltEyes of Prop
	(properties
		y 74
		x 76
		view 139
		loop 4
	)
)

(instance johnnyMouth of Prop
	(properties
		y 78
		x 235
		view 139
		loop 6
	)
)

(instance johnnyEyes of Prop
	(properties
		y 69
		x 246
		view 139
		loop 5
	)
)

(instance soundDice of Sound
	(properties
		number 76
		priority 2
	)
)

(instance dieBox_b of View
	(properties
		view 39
		loop 2
	)
)

(instance rollDieScript_b of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					cel: (Random 0 (client lastCel:))
					PromptForDiskChange: 1
				)
				(client hide: select: 0)
			)
			(1 (= seconds 2))
			(2 (client setScript: 0))
		)
	)
)

(class Die_b of Prop
	(properties
		sel_327 0
		sel_328 1
		sel_329 0
		PromptForDiskChange 0
	)
	
	(method (init)
		(super init:)
		((= sel_329 (Clone dieBox_b))
			x: (self x?)
			y: (self y?)
			stopUpd:
			init:
		)
		(self select: sel_327)
		(self stopUpd:)
	)
	
	(method (sel_330)
		(if sel_327 (self setScript: (Clone rollDieScript_b)))
	)
	
	(method (select param1)
		(if
		(= sel_327 (if argc param1 else (not sel_327)))
			(sel_329 show:)
		else
			(sel_329 hide:)
		)
	)
)

(class DiceGame_b of List
	(properties
		sel_327 0
		view 0
		loop 0
	)
	
	(method (init param1 &tmp temp0 newDie)
		(if argc
			(= temp0 0)
			(while (<= temp0 (- argc 2))
				((= newDie (Die_b new:))
					posn: [param1 temp0] [param1 (++ temp0)]
					view: view
					loop: loop
					init:
					hide:
				)
				(newDie setCel: (Random 0 (newDie lastCel:)))
				(self add: newDie)
				(++ temp0)
			)
		)
		(super init:)
	)
	
	(method (dispose)
		(self release:)
		(super dispose:)
	)
	
	(method (cue)
		(self eachElementDo: #cue &rest)
	)
)

(class oldSaltDice_b of DiceGame_b
	(properties
		sel_332 0
		sel_333 0
	)
	
	(method (sel_334 &tmp temp0 temp1 theSel_333 theSel_333_2)
		(= sel_333 0)
		(Sort self reverseLT_a)
		(= temp0 (Count self diceCount_a (self at: 2)))
		(= theSel_333 (+ ((self at: 2) cel?) 1))
		(switch temp0
			(5
				(= sel_332 (+ 500 (* 10 theSel_333)))
			)
			(4
				(= sel_332 (+ 400 (* 10 theSel_333)))
			)
			(else 
				(= temp0 (Count self diceCount_a (self at: 1)))
				(= temp1 (Count self diceCount_a (self at: 3)))
				(= theSel_333 (+ ((self at: 1) cel?) 1))
				(= theSel_333_2 (+ ((self at: 3) cel?) 1))
				(switch temp0
					(3
						(if (== temp1 2)
							(= sel_332 (+ 300 (* 10 theSel_333) theSel_333_2))
						else
							(= sel_332 (+ 200 (* 10 theSel_333)))
						)
					)
					(2
						(cond 
							((== temp1 3) (= sel_332 (+ 300 (* 10 theSel_333_2) theSel_333)))
							((== temp1 2)
								(= sel_332 (+ 100 (* 10 theSel_333) theSel_333_2))
								(= sel_333 theSel_333)
							)
							(else (= sel_332 (* 10 theSel_333)) (= sel_333 theSel_333))
						)
					)
					(else 
						(cond 
							((== temp1 3) (= sel_332 (+ 200 (* 10 theSel_333_2))))
							((== temp1 2) (= sel_332 (* 10 theSel_333_2)) (= sel_333 theSel_333_2))
							(else (= sel_332 0))
						)
					)
				)
			)
		)
	)
)

(instance reverseLT_b of Code
	(properties)
	
	(method (doit param1 param2)
		(return (> (param1 cel?) (param2 cel?)))
	)
)

(instance diceCount_b of Code
	(properties)
	
	(method (doit param1 param2)
		(return (== (param1 cel?) (param2 cel?)))
	)
)

(class ForwardCounter of Forward
	(properties
		cycleCnt 0
		completed 0
		count 1
	)
	
	(method (init param1 theCount theCaller)
		(super init: param1)
		(if (>= argc 2)
			(= count theCount)
			(if (>= argc 3) (= caller theCaller))
		)
	)
	
	(method (cycleDone)
		(if (-- count)
			(super cycleDone:)
		else
			(= completed 1)
			(self motionCue:)
		)
	)
)

(instance pickJohnnyDice of Script
	(properties)
	
	(method (doit)
		(if
		(and (not (johnTwitch register?)) (< (self state?) 1))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (= cycles 10))
			(2
				(Print 39 0 #at -1 5 #width 290 #dispose)
				(= cycles 10)
			)
			(3
				(= local0 1)
				(johnnyLArmP setLoop: 5 setCycle: CycleTo 3 1 self)
			)
			(4
				(greenTumble setLoop: 5 setCel: 0 hide:)
				(greenTable setCel: 0)
				(johnnyLArmP setCycle: EndLoop self)
			)
			(5
				(johnnyDice eachElementDo: #sel_330)
				(johnnyLArmP stopUpd:)
				(= cycles 2)
			)
			(6
				(johnnyLArmP setLoop: 9 setCel: 255 setCycle: BegLoop self)
				(= register (Random 2 4))
			)
			(7
				(johnnyRArmP setLoop: 6 setCel: 0)
				(soundDice number: (SoundFX 76) loop: 1 play:)
				(= cycles 1)
			)
			(8
				(johnnyRArmP setLoop: 6 setCel: 1)
				(soundDice number: (SoundFX 77) loop: 1 play:)
				(= cycles 1)
			)
			(9
				(if register
					(-- register)
					(self changeState: 7)
				else
					(= cycles 1)
				)
			)
			(10
				(soundDice number: (SoundFX 77) loop: 1 play:)
				(johnnyRArmP setLoop: 7 setCycle: EndLoop self)
			)
			(11
				(johnnyRArmP stopUpd:)
				(greenTumble show: setCycle: EndLoop)
				(= local0 0)
				(soundDice number: (SoundFX 89) loop: 1 play:)
				(= seconds 4)
			)
			(12
				(johnnyDice eachElementDo: #show sel_327: 0 cue:)
				(greenTumble stopUpd:)
				(greenTable stopUpd:)
				(= cycles 2)
			)
			(13
				(johnnyRArmP setLoop: 7 setCycle: BegLoop self)
			)
			(14
				(johnnyRArmP stopUpd:)
				(= cycles 2)
			)
			(15 (curRoom newRoom: 56))
		)
	)
)
