;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1)
(include game.sh)
(use Main)
(use Intrface)
(use Osc)
(use LoadMany)
(use Reverse)
(use Extra)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	theDemo 0
)

(local
	printRet
	quitCued
	saveBits
	armsGrabbing
	musicCued
	paletteCued
	local6
)
(procedure (DoDisplay args &tmp theMode theForeFont theBackFont theWidth theX theY theForeColor theBackColor i)
	(return
		(if (== argc 1)
			(Display 1 1 p_restore [args 0])
		else
			(= theX (= theY -1))
			(= theMode teJustCenter)
			(= theForeFont 70)
			(= theBackFont 73)
			(= theWidth 160)
			(= theForeColor colWhite)
			(= theBackColor 0)
			(for ((= i 1)) (< i argc) ((++ i))
				(switch [args i]
					(#mode
						(= theMode [args (++ i)])
					)
					(#font
						(= theBackFont
							(+
								(= theForeFont [args (++ i)])
								1
							)
						)
					)
					(#width
						(= theWidth [args (++ i)])
					)
					(#at
						(= theX [args (++ i)])
						(= theY [args (++ i)])
					)
					(#color
						(= theForeColor [args (++ i)])
					)
					(#back
						(= theBackColor [args (++ i)])
					)
				)
			)
			(= i
				(Display [args 0]
					p_at theX theY
					p_color theBackColor
					p_width theWidth
					p_mode theMode
					p_font theBackFont
					p_save
				)
			)
			(Display [args 0]
				p_at theX theY
				p_color theForeColor
				p_width theWidth
				p_mode theMode
				p_font theForeFont
			)
			(return i)
		)
	)
)

(instance theDemo of Room
	(properties
		style FADEOUT
	)
	
	(method (init &tmp temp0)
		(Load SCRIPT OSC)
		(Load PICTURE 114)
		(LoadMany VIEW 114 216)
		(LoadMany SOUND 801 816 319 817 818 806 805 804)
		(super init:)
		(theIconBar disable:)
		(HandsOff)
		(theGame setCursor: 901 FALSE 1000 1000)
		(self setScript: demo1)
	)
	
	(method (doit)
		(super doit:)
		(if quitCued
			(switch
				(= printRet
					(Print 1 0
						#mode teJustCenter
						#title {Space Quest I: Sarien Encounter}
						#button {Continue} 0
						#button {Restart} 2
						#button {Quit} 3
						#icon 946
					)
				)
				(2
					(cast eachElementDo: #dispose)
					(theGame restart:)
				)
				(3
					(= quit TRUE)
				)
			)
			(theGame setCursor: 901 0 1000 1000)
			(= quitCued FALSE)
			((User curEvent?) type: 0)
		)
		(if ((User curEvent?) type?)
			(theGame setCursor: ARROW_CURSOR TRUE 153 133)
			(= quitCued TRUE)
		)
	)
)

(instance demo1 of Script
	(method (doit)
		(super doit: &rest)
		(if (and musicCued (== ((ScriptID 0 5) prevSignal?) 10))
			(= musicCued FALSE)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: 14)
				((ScriptID 0 5) number: 819 loop: 1 playBed: self)
				(armsL init: setPri: 14)
				(armsR init:)
				(star1 setCycle: Oscillate 20 init:)
				(star2 setCycle: Oscillate 20 init:)
				(star3 setCycle: Oscillate 20 init:)
			)
			(1
				((ScriptID 0 5) number: 801 loop: -1 playBed:)
				(= seconds 2)
			)
			(2 (= musicCued 1))
			(3
				(Sting play:)
				(curRoom overlay: 114 WIPEUP)
				(= seconds 3)
			)
			(4
				(DrawPic 14 WIPEUP)
				(= seconds 2)
			)
			(5
				((ScriptID 0 6) number: 802 loop: 0 play:)
				(armsL setCycle: BegLoop)
				(armsR setCycle: EndLoop self)
			)
			(6
				(armsR stopUpd:)
				(armsL stopUpd:)
				((ScriptID 0 6) number: 803 loop: 0 play: self)
			)
			(7 (= seconds 1))
			(8
				(star2 hide:)
				(= saveBits
					(DoDisplay
						{In the far reaches of space, there lurks a danger that surpasses any danger that 
						has ever lurked in the farthest reaches of space before...}
						#at 152 5
					)
				)
				(= seconds 8)
			)
			(9
				(DoDisplay saveBits)
				(= cycles 2)
			)
			(10
				(curRoom setScript: demo2)
			)
		)
	)
)

(instance demo2 of Script
	(method (doit)
		(super doit: &rest)
		(if (and musicCued (== ((ScriptID 0 5) prevSignal?) 10))
			(= musicCued FALSE)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LoadMany PICTURE 7 107)
				(armsL setCycle: 0 hide:)
				(armsR setCycle: 0 hide: dispose:)
				(star1 hide:)
				(star3 hide:)
				(curRoom drawPic: 7)
				((ScriptID 0 7) number: 319 loop: -1 play:)
				(star1
					view: 107
					loop: 2
					cel: 0
					x: 277
					y: 61
					setCycle: Oscillate 20
					show:
				)
				(star2
					init:
					view: 107
					loop: 3
					cel: 1
					x: 259
					y: 71
					setCycle: Oscillate 20
					show:
				)
				(globe setCycle: Forward init:)
				(= cycles 2)
			)
			(1
				(= musicCued TRUE)
			)
			(2
				(Sting number: 817 play:)
				(curRoom overlay: 107 WIPELEFT)
				(= seconds 3)
			)
			(3
				(DrawPic 7 WIPELEFT)
				(= seconds 2)
			)
			(4
				(roger
					init:
					view: 0
					setLoop: 7
					posn: 331 223
					setCycle: Forward
					setMotion: MoveTo 263 181 self
				)
			)
			(5
				(roger
					view: 53
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(= saveBits
					(DoDisplay
						{An evil race bent on galactic domination invades your starship and steals
						 the Star Generator, an experimental device capable of destroying entire suns.}
						#at 10 5
					)
				)
				(= seconds 9)
			)
			(7
				(DoDisplay saveBits)
				(= cycles 1)
			)
			(8
				(= cycles 2)
			)
			(9
				(curRoom setScript: demo3)
			)
		)
	)
)

(instance demo3 of Script
	(method (doit)
		(super doit: &rest)
		(if (and musicCued (== ((ScriptID 0 5) prevSignal?) 10))
			(= musicCued FALSE)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(star2 setCycle: 0 hide:)
				(roger setLoop: -1 hide:)
				(door hide:)
				(globe dispose:)
				(LoadMany PICTURE 16 116)
				((ScriptID 0 7) fade:)
				(curRoom drawPic: 16)
				(armsL
					view: 216
					setLoop: 2
					setCel: 0
					setPri: 12
					x: 253
					y: 49
					cycleSpeed: 3
					init:
					show:
				)
				(arcada init:)
				(deltaur init:)
				(star1 dispose:)
				(star2
					view: 216
					loop: 4
					cel: 1
					x: 271
					y: 114
					priority: 1
					setCycle: Forward
					init:
					show:
				)
				(star3
					view: 216
					loop: 4
					cel: 2
					x: 105
					y: 154
					priority: 1
					setCycle: Forward
					init:
					show:
				)
				(star4 setCycle: Forward init:)
				(= cycles 2)
			)
			(1
				(= musicCued TRUE)
			)
			(2
				(Sting number: 818 play:)
				(curRoom overlay: 116 WIPEUP)
				(= seconds 3)
			)
			(3
				(DrawPic 16 WIPEUP)
				(star3 stopUpd:)
				(star4 stopUpd:)
				(= seconds 2)
			)
			(4
				(= saveBits
					(DoDisplay
						{You are Roger Wilco, Space Janitor. A man with a destiny. A man with a mission.
						 A man with a sponge mop. Hurtling towards a rendezvous with the unknown.}
						#at 5 25
					)
				)
				(= seconds 9)
			)
			(5
				(Sting dispose:)
				(DoDisplay saveBits)
				(= cycles 1)
			)
			(6
				(= cycles 2)
			)
			(7
				((ScriptID 0 7) number: 804 loop: -1 play:)
				(deltaur
					illegalBits: 0
					setMotion: MoveTo (- (deltaur x?) 50) (- (deltaur y?) 120) deltaur
				)
				(armsL
					setMotion: MoveTo (- (armsL x?) 50) (- (armsL y?) 120)
					setCycle: EndLoop
				)
				(= seconds 4)
			)
			(8
				(tempSound number: 805 loop: 1 play:)
				(arcada setCycle: EndLoop)
				(= cycles 2)
			)
			(9
				((ScriptID 0 6) number: 806 loop: 1 play:)
				(escapePod
					init:
					illegalBits: 0
					setCycle: EndLoop
					setMotion: MoveTo -75 160
				)
				(= seconds 6)
			)
			(10
				(arcada cel: 8)
				((ScriptID 0 5) fade:)
				(tempSound dispose:)
				(LoadMany SOUND 324 401 808 809 810 811)
				(curRoom setScript: demo4)
			)
		)
	)
)

(instance demo4 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(star1 dispose:)
				(star2 dispose:)
				(star3 dispose:)
				(star4 dispose:)
				(star5 dispose:)
				(deltaur dispose:)
				(arcada dispose:)
				((ScriptID 0 6) stop:)
				(armsL dispose:)
				(escapePod dispose:)
				(LoadMany PICTURE 37 137)
				(Load VIEW 56)
				((ScriptID 0 7) stop:)
				(curRoom drawPic: 37)
				((ScriptID 0 5) number: 401 loop: -1 play:)
				((ScriptID 0 6) number: 807 loop: 0 play:)
				(smoke init: setCycle: Forward)
				(grell init:)
				(grellMouth init: hide:)
				(roger
					view: 12
					loop: 0
					cel: 0
					x: 161
					y: 130
					cycleSpeed: 1
					init:
					show:
				)
				(door
					init:
					view: 137
					loop: 1
					cel: 0
					x: 126
					y: 98
					setPri: 12
					cycleSpeed: 1
					init:
					show:
				)
				(brokenGlass init:)
				(= cycles 2)
			)
			(1
				(curRoom overlay: 137 7)
				(= seconds 3)
			)
			(2
				(DrawPic 37 IRISOUT)
				(= cycles 5)
			)
			(3
				((ScriptID 0 6) number: 808 loop: 1 play:)
				((ScriptID 0 7) number: 324 loop: 1 play:)
				(brokenGlass setCycle: Oscillate 1)
				(roger setCycle: CycleTo 4 1 self)
				(door setCycle: CycleTo 2 1)
			)
			(4
				(roger setCycle: EndLoop)
				(door setCycle: EndLoop self)
			)
			(5
				((ScriptID 0 7) stop:)
				(door stopUpd:)
				(= cycles 15)
			)
			(6
				(roger stopUpd:)
				(= cycles 2)
			)
			(7
				(= saveBits
					(DoDisplay
						{You represent the best and last hope of the Xenon race as you
						 stride boldly forward in the face of overwhelming odds.}
						#at 10 5
					)
				)
				(= seconds 7)
			)
			(8
				(DoDisplay saveBits)
				(= cycles 1)
			)
			(9
				(= cycles 2)
			)
			(10
				(roger
					view: 1
					cel: 0
					setLoop: sGroop
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 203 151 self
				)
			)
			(11
				(roger
					view: 56
					setLoop: 0
					cel: 0
					setCycle: CycleTo 6 1 self
				)
			)
			(12
				((ScriptID 0 6) number: 809 loop: 1 play:)
				(roger
					view: 56
					setLoop: 0
					cel: 7
					setCycle: EndLoop self
				)
			)
			(13
				(roger
					posn: (+ (roger x?) 33) (+ (roger y?) 11)
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(14
				((ScriptID 0 6) number: 810 loop: 1 play:)
				(roger
					view: 1
					setLoop: 4
					cycleSpeed: 0
					setCycle: Forward
					setMotion: MoveTo 250 175 self
				)
			)
			(15
				(roger setMotion: MoveTo 340 225)
				(grell setCycle: CycleTo 7 1 self)
			)
			(16
				(grellMouth show:)
				(grell setCel: 255)
				(= cycles 1)
			)
			(17
				((ScriptID 0 6) number: 811 loop: 1 play:)
				(grellMouth setCycle: Forward)
				(= cycles 2)
			)
			(18
				(= saveBits
					(DoDisplay
						{The natives will be hungry for your company.}
						#at 10 5
					)
				)
				(= seconds 5)
			)
			(19
				(DoDisplay saveBits)
				(= cycles 1)
			)
			(20
				(= cycles 2)
			)
			(21
				((ScriptID 0 5) fade:)
				((ScriptID 0 6) stop:)
				(curRoom setScript: demo5)
			)
		)
	)
)

(instance demo5 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(door dispose:)
				(grell dispose:)
				(smoke dispose:)
				(brokenGlass dispose:)
				(grellMouth dispose:)
				(door hide:)
				(LoadMany PICTURE 43 143)
				((ScriptID 0 5) number: 611 loop: -1 playBed:)
				(curRoom drawPic: 43)
				(roger
					init:
					show:
					cycleSpeed: 0
					view: 1
					posn: -10 165
					cel: 0
					setLoop: sGroop
					setCycle: Walk
					setMotion: MoveTo 70 180 self
				)
				(drummer init: setScript: forwardScript)
				(zzLeft init: setCycle: Forward)
				(zzRight init: setCycle: Forward)
				(kzittenarm init:)
				(kzittenhead init:)
				(fenris init:)
				(bartender init: addToPic:)
				(bartenderHead init: setCycle: Forward)
				(greenGirl init:)
				(slotguy init: addToPic:)
				(chip_Dale init: addToPic:)
				(drum init: addToPic:)
				(kzitten init: addToPic:)
				(fenrisbody init: addToPic:)
				(yellowGuy init: setLoop: 0 cycleSpeed: 2 setCycle: EndLoop)
			)
			(1
				(roger setCycle: 0 setCel: 0)
				(curRoom overlay: 143 WIPELEFT)
				(= seconds 5)
			)
			(2
				(DrawPic 43 WIPELEFT)
				(yellowGuy init: setCycle: BegLoop)
				(slotguy init: addToPic:)
				(chip_Dale init: addToPic:)
				(drum init: addToPic:)
				(kzitten init: addToPic:)
				(fenrisbody init: addToPic:)
				(bartender init: addToPic:)
				(= seconds 2)
			)
			(3
				(= saveBits
					(DoDisplay
						{You'll find new friends in unlikely places.}
						#at 5 5
						#width 320
					)
				)
				(= seconds 4)
			)
			(4
				(DoDisplay saveBits)
				(= cycles 1)
			)
			(5 (= cycles 2))
			(6
				(= saveBits
					(DoDisplay {But not here.}
						#at 5 5
					)
				)
				(= seconds 3)
			)
			(7
				(DoDisplay saveBits)
				(= cycles 1)
			)
			(8
				(= cycles 2)
			)
			(9
				((ScriptID 0 5) fade:)
				((ScriptID 0 6) vol: 127)
				(curRoom setScript: demo6)
			)
		)
	)
)

(instance demo6 of Script
	(method (doit)
		(super doit: &rest)
		(if paletteCued
			(Palette PALCycle 205 255 -1)
		)
		(if
			(and
				(== state 0)
				(== ((ScriptID 0 5) prevSignal?) 10)
			)
			((ScriptID 0 5) prevSignal: 0)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LoadMany PICTURE 13 113)
				(bartenderHead dispose:)
				(drummer dispose:)
				(zzLeft dispose:)
				(zzRight dispose:)
				(kzitten dispose:)
				(kzittenarm dispose:)
				(kzittenhead dispose:)
				(chip_Dale dispose:)
				(fenris dispose:)
				(fenrisbody dispose:)
				(yellowGuy dispose:)
				(drum dispose:)
				(bartender dispose:)
				(greenGirl dispose:)
				(roger hide:)
				(egoHead init: setCel: 0)
				(arm1 init: show: setLoop: 1 setCel: 2 stopUpd:)
				(seatbelt init:)
				(curRoom drawPic: 13)
				((ScriptID 0 5) number: 814 loop: 1 playBed: self)
			)
			(1
				(= saveBits
					(DoDisplay
						{Step into Roger Wilco's boots and learn the importance of proper
						 safety procedures as you confront the ultimate evil in Sierra On-Line's
						 \nSPACE QUEST I\nRoger Wilco and The Sarien Encounter.}
						#at 10 5
					)
				)
				(= seconds 13)
			)
			(2
				(DoDisplay saveBits)
				(= cycles 1)
			)
			(3
				(seatbelt init:)
				(screen init: setCycle: Forward)
				(dials init: setScript: forwardScript)
				(= seconds 3)
			)
			(4
				(arm1 setLoop: 3 setCel: 0)
				(= cycles 2)
			)
			(5
				(egoHead setCel: 1)
				(arm1 setCel: 1)
				(= cycles 2)
			)
			(6
				((ScriptID 0 6) number: 337 loop: 1 play:)
				(arm1 setCel: 2)
				(seatbelt setCel: 3)
				(= cycles 2)
			)
			(7
				(arm1 setCel: 3)
				(seatbelt setCel: 4)
				(= cycles 2)
			)
			(8
				(arm1 setCel: 4)
				(seatbelt setCel: 5)
				(= cycles 2)
			)
			(9
				(egoHead setCel: 0)
				(arm1 setCel: 5)
				(seatbelt setCel: 6)
				(= cycles 2)
			)
			(10
				(screen setCycle: 0 stopUpd:)
				(arm1 dispose:)
				(seatbelt2 init:)
				(seatbelt setCel: 7)
				((ScriptID 0 6) number: 325 loop: 1 play:)
				(roger
					view: 113
					posn: (arm1 x?) (arm1 y?)
					show:
					setLoop: 1
					setCycle: EndLoop self
				)
			)
			(11
				(roger stopUpd:)
				(dials setScript: 0 stopUpd:)
				(= cycles 2)
			)
			(12
				(= paletteCued TRUE)
				(curRoom overlay: 113 WIPEDOWN)
				(= seconds 8)
			)
			(13
				(= paletteCued FALSE)
				(DrawPic 13 WIPEDOWN)
				(roger setCycle: BegLoop)
				(= cycles 1)
			)
			(14
				(egoHead setCel: 1)
				(roger setLoop: 2 setCycle: EndLoop self)
			)
			(15
				(= seconds 2)
			)
			(16
				(roger setCycle: BegLoop)
				(if (== ((ScriptID 0 5) prevSignal?) -1)
					(= cycles 1)
				)
			)
			(17
				((ScriptID 0 7) vol: 127)
				(cast eachElementDo: #dispose)
				(theGame restart:)
			)
		)
	)
)

(instance forwardScript of Script
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(= state -1)
				(if (Random 0 3)
					(client
						cycleSpeed: (Random 0 1)
						setCycle: (if (Random 0 1) Forward else Reverse)
					)
				else
					(client setCel: (Random 0 2))
				)
				(= cycles (Random 2 6))
			)
		)
	)
)

(instance armsL of Actor
	(properties
		x 201
		y 78
		view 114
		cel 2
		signal (| ignrAct ignrHrz)
		cycleSpeed 6
		xStep 1
	)
	
	(method (setMotion)
		(super setMotion: &rest)
		(if (not armsGrabbing)
			(= armsGrabbing TRUE)
			((ScriptID 0 6) number: 802 loop: 0 play:)
		)
	)
)

(instance armsR of Actor
	(properties
		x 159
		y 98
		view 114
		loop 3
		signal (| ignrAct ignrHrz)
		cycleSpeed 6
		xStep 1
	)
)

(instance star1 of Prop
	(properties
		x 39
		y 156
		view 114
		loop 1
		cel 1
		cycleSpeed 1
	)
)

(instance star2 of Prop
	(properties
		x 279
		y 15
		view 114
		loop 1
		cel 2
		cycleSpeed 2
	)
)

(instance star3 of Prop
	(properties
		x 280
		y 176
		view 114
		loop 2
		cel 4
		cycleSpeed 1
	)
)

(instance star4 of Prop
	(properties
		x 83
		y 142
		view 216
		loop 5
		cel 3
		priority 1
		signal fixPriOn
		cycleSpeed 1
	)
)

(instance star5 of Prop
	(properties
		x 36
		y 90
		view 216
		loop 5
		cel 3
		priority 1
		signal fixPriOn
		cycleSpeed 2
	)
)

(instance arcada of Actor
	(properties
		x 247
		y 100
		view 216
		signal (| ignrAct ignrHrz fixedLoop)
		cycleSpeed 1
		xStep 1
	)
)

(instance deltaur of Actor
	(properties
		x 253
		y 49
		view 216
		loop 1
		signal (| ignrAct ignrHrz fixedLoop)
		xStep 1
	)
	
	(method (cue)
		((ScriptID 0 7) stop:)
	)
)

(instance escapePod of Actor
	(properties
		x 223
		y 70
		yStep 12
		view 216
		loop 3
		signal (| ignrAct ignrHrz fixedLoop)
		xStep 25
	)
)

(instance smoke of Prop
	(properties
		x 195
		y 57
		view 137
		cycleSpeed 2
	)
)

(instance brokenGlass of Prop
	(properties
		x 96
		y 151
		view 137
		loop 2
	)
)

(instance grell of Prop
	(properties
		x 256
		y 172
		view 420
		cycleSpeed 2
	)
)

(instance roger of Actor
	(properties
		x 256
		y 174
		view 53
		signal ignrAct
		illegalBits $0000
	)
)

(instance globe of Prop
	(properties
		x 296
		y 112
		view 107
		cel 1
		priority 8
		signal fixPriOn
	)
)

(instance door of Prop
	(properties
		x 318
		y 153
		view 107
		loop 1
	)
)

(instance arm1 of Prop
	(properties
		x 194
		y 86
		view 113
		loop 1
		signal ignrAct
		cycleSpeed 2
	)
)

(instance seatbelt of Prop
	(properties
		x 276
		y 26
		view 113
		loop 4
		signal ignrAct
		cycleSpeed 2
	)
)

(instance seatbelt2 of Prop
	(properties
		x 194
		y 86
		view 113
		loop 3
		cel 6
		signal ignrAct
	)
)

(instance screen of Prop
	(properties
		x 108
		y 117
		view 113
		loop 5
		cycleSpeed 1
	)
)

(instance dials of Prop
	(properties
		x 42
		y 112
		view 113
		loop 6
		cel 1
	)
)

(instance egoHead of View
	(properties
		x 225
		y 63
		view 113
	)
)

(instance drummer of Prop
	(properties
		x 118
		y 49
		view 433
		cel 5
		priority 2
		signal (| ignrAct fixPriOn)
	)
)

(instance zzLeft of Prop
	(properties
		x 133
		y 93
		view 433
		loop 1
		cel 1
		priority 6
		signal (| ignrAct fixPriOn)
		cycleSpeed 1
	)
)

(instance zzRight of Prop
	(properties
		x 161
		y 88
		view 433
		loop 2
		cel 4
		priority 5
		signal (| ignrAct fixPriOn)
		cycleSpeed 1
	)
)

(instance drum of View
	(properties
		x 112
		y 67
		view 433
		loop 5
		priority 3
		signal (| ignrAct fixPriOn)
	)
)

(instance kzitten of View
	(properties
		x 108
		y 151
		view 436
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance kzittenarm of Prop
	(properties
		x 119
		y 143
		view 436
		loop 3
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance kzittenhead of Prop
	(properties
		x 108
		y 151
		view 436
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance chip_Dale of Prop
	(properties
		x 201
		y 185
		view 437
		priority 15
		signal (| ignrAct fixPriOn)
		name "chip&Dale"
	)
)

(instance fenris of Extra
	(properties
		x 215
		y 146
		view 438
		priority 13
		signal (| ignrAct fixPriOn)
	)
)

(instance fenrisbody of View
	(properties
		x 215
		y 146
		view 438
		loop 3
		priority 13
		signal (| ignrAct fixPriOn)
	)
)

(instance greenGirl of Extra
	(properties
		x 290
		y 117
		view 439
		cel 7
		priority 9
		signal fixPriOn
	)
)

(instance yellowGuy of Prop
	(properties
		x 237
		y 117
		view 440
		priority 9
		signal (| ignrAct fixPriOn)
	)
)

(instance bartenderHead of Prop
	(properties
		x 264
		y 138
		view 442
		loop 2
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance bartender of View
	(properties
		x 276
		y 172
		view 442
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance slotguy of Extra
	(properties
		x 265
		y 102
		view 443
		cel 4
	)
)

(instance grellMouth of Prop
	(properties
		x 268
		y 128
		view 420
		loop 2
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 1
	)
)

(instance Sting of Sound
	(properties
		number 816
	)
	
	(method (play)
		(super play: &rest)
		((ScriptID 0 5) pause:)
	)
	
	(method (check)
		(super check: &rest)
		(if (== prevSignal -1)
			((ScriptID 0 5) pause: 0)
		)
	)
)

(instance tempSound of Sound
	(properties
		number 805
	)
)
