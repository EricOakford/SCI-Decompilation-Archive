;;; Sierra Script 1.0 - (do not remove this comment)
(script# 740)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use Plane)
(use Array)
(use Print)
(use Cursor)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm740 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6 =  -1
	local7 =  1
	local8
	local9
	local10
	local11
	local12
	cartoonPropView
	theRegister
	theRegister_2
	local16
	local17
	local18
	local19 =  -1
	local20 =  40
	local21
	local22 =  -1
	local23
	theTicks =  7
	local25
	local26
	local27
	[local28 2]
	local30
	local31
	local32
	[local33 18] = [741 230 380 440 442 511 514 560 860 320 420 580 430 840 310 580 337 741]
)
(procedure (localproc_008c &tmp theTheCursor)
	(= theTheCursor theCursor)
	(SetFontRes 640 480)
	(theGame setCursor: normalCursor 1)
	(SetCursor 230 105)
	(if
		(Print
			font: userFont
			width: 110
			addTitle: 5 0 4 1 0
			addText: 5 0 0 1 50 3 0
			addButton: 0 5 0 3 1 50 35 0
			addButton: 1 5 0 2 1 135 35 0
			addIcon: 1908 0 0 2 2
			init:
		)
		(if
			(and
				(DoSound sndSET_SOUND)
				(== (curRoom script?) scrollText)
				(or (ResCheck 140 1004) (ResCheck 141))
			)
			(sfx number: 1004 loop: 1 play: quitCue)
		else
			(= quit 1)
		)
	else
		(theMusic pause: 0)
		(theGame setCursor: theTheCursor)
	)
	(SetFontRes 320 200)
)

(class cObj of Obj
	(properties
		scratch 0
	)
)

(instance rm740 of LarryRoom
	(properties
		picture 740
		autoLoad 0
		noControls 1
	)
	
	(method (init)
		(theIconBar hide:)
		(SetFontRes 320 200)
		(thePlane setRect: 0 0 319 199)
		(thePlane drawPic: -1)
		(super init: &rest)
		(cartoonProp init: hide:)
		(ego init: hide:)
		(theMusic number: 740)
		(theMusic loop: 1 play: showCartoon)
		(= local32
			(IntArray
				newWith:
					10
					100
					110
					95
					105
					100
					60
					90
					40
					70
					105
					100
					80
					80
					50
					15
					60
					50
					25
					65
					100
					30
					0
					10
					20
					40
					90
					80
					50
					70
					90
					-1
			)
		)
		(= local4
			(IntArray
				newWith: 3 3 3 4 4 3 5 4 3 3 5 4 5 4 5 3 3 3 5 6 1 3 3 3 3 3 3 3 3 3 3 -1
			)
		)
		(= local3 (IntArray newWith: 15 77 69 73 50 45 63 17 63))
		(keyDownHandler addToFront: self)
		(mouseDownHandler add: self)
		(theGame setCursor: invCursor)
		(cond 
			((== global100 740) (self setScript: scrollText))
			((== global100 741) (self setScript: playMusicScr))
			(else (self setScript: showCartoon))
		)
	)
	
	(method (dispose)
		(if (keyDownHandler contains: self)
			(keyDownHandler delete: self)
		)
		(if (mouseDownHandler contains: self)
			(mouseDownHandler delete: self)
		)
		(thePlane setRect: 0 15 320 154)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event type?)
			(event claimed: 1)
			(theMusic pause: 1)
			(localproc_008c)
		)
	)
)

(instance cartoonProp of Prop
	(properties
		x 100
		y 20
		priority 14
		fixPriority 1
		view 740
		cycleSpeed 9
	)
)

(instance fireWorks of Prop
	(properties
		view 789
	)
	
	(method (init)
		(super init:)
		(self setPri: (- (cartoonProp priority?) 1))
	)
)

(instance scrollText of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= ticks 240))
			(1
				(if (!= (theMusic number?) 741)
					(theMusic number: 741 loop: 1 play: musicCue)
				)
				(= cycles 2)
			)
			(2
				(= register 1)
				(= local6 -1)
				(= ticks 120)
			)
			(3 (self cue:))
			(4
				(thePlane drawPic: -1)
				(if (== (= local5 (local4 at: (++ local6))) -1)
					(theMusic loop: 1 stop:)
					(= next playMusicScr)
					(self dispose:)
				else
					(while (== local0 local11)
						(= local11 (local3 at: (Random 0 7)))
					)
					(= local0 local11)
					(= local8 (/ (- 185 (* local5 24)) 2))
					(= local9 (local32 at: local6))
				)
				(self cue:)
			)
			(5
				(= temp0 (ByteArray new: 120))
				(Message 0 740 0 0 0 register (temp0 data?))
				(myCreditText
					fore: local11
					back: 0
					font: 900
					mode: 1
					modeless: 2
					posn: local9 local8
					width: 320
					addText: (temp0 data?)
					plane: ((Plane new:) picture: -2 yourself:)
					init:
				)
				(= cycles 1)
			)
			(6
				(switch register
					(10
						(messager say: 2 2 5 1 0 580)
					)
					(30
						(theMusic client: 0 fade: 0 10 5 1)
						(DoAudio 2 610 1 4 68 3)
					)
				)
				(= seconds (= local31 (* local5 2)))
			)
			(7
				(= local30 (Random 0 3))
				(++ register)
				(= seconds 3)
			)
			(8
				(if (myCreditText dialog?)
					((myCreditText dialog?) dispose:)
				)
				(= state 3)
				(self cue:)
			)
		)
	)
)

(instance rangeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cartoonProp show:)
				(= theRegister register)
				(self cue:)
			)
			(1
				(if (== local7 -1)
					(cartoonProp
						cel: (cartoonProp lastCel:)
						setCycle: Beg self
					)
				else
					(cartoonProp cel: 0 setCycle: End self)
				)
			)
			(2 (= ticks 1))
			(3
				(if (-- theRegister)
					(cartoonProp view: (+ (cartoonProp view?) local7))
					(= state 0)
				)
				(self cue:)
			)
			(4
				(= ticks (Max 1 (cartoonProp cycleSpeed?)))
			)
			(5 (self dispose:))
		)
	)
)

(instance loopRangeScr of Script
	(properties)
	
	(method (dispose)
		(if script (script caller: 0))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theRegister_2 register)
				(= cartoonPropView (cartoonProp view?))
				(self cue:)
			)
			(1
				(cartoonProp view: cartoonPropView)
				(self setScript: rangeScr self local12)
			)
			(2 (= ticks 1))
			(3
				(if (-- theRegister_2)
					(= state 0)
					(self cue:)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance modifyPaletteScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== local16 -1)
					(= register 100)
				else
					(= register 40)
				)
				(= ticks 10)
			)
			(1
				(cond 
					((and (== local16 -1) (> register local20)) (-- register) (-- state))
					((and (== local16 1) (< register 100)) (++ register) (-- state))
				)
				(cond 
					(local25 (Palette 2 0 79 register) (Palette 2 120 255 register))
					((not local21) (Palette 2 80 119 register))
					(else (Palette 2 0 255 register))
				)
				(= ticks theTicks)
			)
			(2 (self dispose:))
		)
	)
)

(instance cartoonManagerScr of Script
	(properties)
	
	(method (cue)
		(switch (++ local19)
			(0
				(if (!= (showCartoon state?) 13)
					(if (cartoonProp script?)
						((cartoonProp script?) caller: 0)
						((cartoonProp script?) dispose:)
					)
					(showCartoon state: 10 cycles: 0 ticks: 0 register: 1)
					(cartoonProp hide:)
				)
				(showCartoon cue:)
			)
			(1 (fireWorksScr register: 1))
			(2
				(showCartoon state: 74 ticks: 0 cycles: 1)
				(if (cartoonProp script?)
					((cartoonProp script?) caller: 0)
					((cartoonProp script?) dispose:)
				)
				(cartoonProp hide: setCycle: 0)
			)
			(3
				(if (!= (showCartoon state?) 93)
					(if (cartoonProp script?)
						((cartoonProp script?) caller: 0)
						((cartoonProp script?) dispose:)
					)
					(showCartoon ticks: 0 cycles: 0 state: 95)
					(cartoonProp hide:)
				)
				(showCartoon cue:)
			)
		)
	)
)

(instance showCartoon of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= next scrollText)
				(= register 0)
				(= cycles 2)
			)
			(1
				(Load rsVIEW 740 741 742 743 744)
			)
			(2
				(theMusic client: cartoonManagerScr)
				(= local7 -1)
				(= local16 -1)
				(ego setScript: modifyPaletteScr)
				(cartoonProp
					view: 744
					cycleSpeed: 9
					posn:
						(/ (- 320 (CelWide 740 0 0)) 2)
						(/ (- 190 (CelHigh 740 0 0)) 2)
					init:
					setScript: rangeScr self 5
				)
			)
			(3
				(cartoonProp hide:)
				(= ticks 10)
			)
			(4
				(UnLoad 128 740 741 742 743 744)
				(= cycles 2)
			)
			(5
				(Load rsVIEW 745 746)
				(= cycles 2)
			)
			(6
				(= local7 1)
				(cartoonProp
					view: 745
					posn: (Random 15 200) (Random 10 100)
					setScript: rangeScr self 2
				)
			)
			(7
				(cartoonProp hide:)
				(= ticks 10)
			)
			(8
				(UnLoad 128 745 746)
				(= cycles 2)
			)
			(9
				(Load rsVIEW 747 748 749 750 751 752)
				(= cycles 2)
			)
			(10
				(= local7 1)
				(cartoonProp
					view: 747
					cycleSpeed: 6
					posn: (Random 15 200) (Random 10 100)
					setScript: rangeScr self 6
				)
			)
			(11
				(cartoonProp hide:)
				(if register (= cycles 2) else (= ticks 10))
			)
			(12
				(UnLoad 128 747 748 749 750 751 752)
				(= cycles 2)
			)
			(13
				(Load rsVIEW 753 754)
				(if register (= cycles 2))
			)
			(14
				(cartoonProp
					view: 753
					cycleSpeed: 9
					posn: (Random 15 200) (Random 10 100)
					cel: 0
					show:
					setCycle: End self
				)
			)
			(15 (= ticks 1))
			(16
				(cartoonProp view: 754 cel: 0 setCycle: CT 4 1 self)
			)
			(17
				(= ticks (Max 1 (cartoonProp cycleSpeed?)))
			)
			(18
				(cartoonProp cel: 3)
				(= ticks (Max 1 (cartoonProp cycleSpeed?)))
			)
			(19
				(cartoonProp cel: 4)
				(= ticks (Max 1 (cartoonProp cycleSpeed?)))
			)
			(20
				(cartoonProp cel: 3)
				(= ticks (Max 1 (cartoonProp cycleSpeed?)))
			)
			(21
				(cartoonProp cel: 4)
				(= ticks (Max 1 (cartoonProp cycleSpeed?)))
			)
			(22
				(cartoonProp cel: 3)
				(= ticks (Max 1 (cartoonProp cycleSpeed?)))
			)
			(23
				(cartoonProp cel: 4)
				(= ticks (Max 1 (cartoonProp cycleSpeed?)))
			)
			(24
				(cartoonProp cel: 3)
				(= ticks (Max 1 (cartoonProp cycleSpeed?)))
			)
			(25
				(cartoonProp setCycle: End self)
			)
			(26
				(cartoonProp hide:)
				(= ticks (Max 1 (cartoonProp cycleSpeed?)))
			)
			(27
				(UnLoad 128 755 756)
				(= cycles 2)
			)
			(28
				(Load rsVIEW 755 756)
				(= cycles 2)
			)
			(29
				(= local7 1)
				(cartoonProp
					view: 755
					setCel: 0
					posn: (Random 15 200) (Random 10 100)
					setScript: rangeScr self 2
				)
			)
			(30
				(cartoonProp hide:)
				(= ticks 10)
			)
			(31
				(UnLoad 128 753 754)
				(= cycles 2)
			)
			(32
				(Load rsVIEW 757 758 759 760 761)
				(= cycles 2)
			)
			(33
				(= local7 1)
				(cartoonProp
					view: 757
					posn: (Random 15 200) (Random 10 100)
					setScript: rangeScr self 5
				)
			)
			(34
				(cartoonProp hide:)
				(= ticks 10)
			)
			(35
				(UnLoad 128 757 758 759 760 761)
				(= cycles 2)
			)
			(36
				(Load rsVIEW 762 763 764)
				(= cycles 2)
			)
			(37
				(= local7 1)
				(cartoonProp
					view: 762
					posn: (Random 15 200) (Random 10 100)
					setScript: rangeScr self 3
				)
			)
			(38
				(cartoonProp hide:)
				(= ticks 10)
			)
			(39
				(UnLoad 128 762 763 764)
				(= cycles 2)
			)
			(40
				(if (> howFast 11)
					(fireWorks init: setScript: fireWorksScr)
				)
				(self cue:)
			)
			(41
				(Load rsVIEW 765 766 767)
				(= cycles 2)
			)
			(42
				(= local7 1)
				(cartoonProp
					view: 765
					posn: 45 100
					cycleSpeed: 4
					setScript: rangeScr self 2
				)
				(= register 4)
			)
			(43
				(cartoonProp view: 767 cel: 0 setCycle: CT 5 1 self)
			)
			(44
				(if (-- register)
					(cartoonProp view: 766 cel: 0 setCycle: End self)
					(= state 42)
				else
					(self cue:)
				)
			)
			(45
				(cartoonProp hide:)
				(= ticks 10)
			)
			(46
				(UnLoad 128 762 763 764)
				(= cycles 2)
			)
			(47
				(Load rsVIEW 768 769 770)
				(= cycles 2)
			)
			(48
				(= local7 1)
				(cartoonProp
					view: 768
					posn: 83 84
					cycleSpeed: 6
					setScript: rangeScr self 3
				)
			)
			(49 (= ticks 1))
			(50
				(cartoonProp view: 769 cel: 0 setScript: rangeScr self 2)
			)
			(51 (= ticks 1))
			(52
				(cartoonProp view: 769 cel: 0 setScript: rangeScr self 2)
			)
			(53
				(cartoonProp hide:)
				(= ticks 10)
			)
			(54
				(UnLoad 128 762 763 764)
				(= cycles 2)
			)
			(55
				(Load rsVIEW 771 772 773 774)
				(= cycles 2)
			)
			(56
				(= local7 1)
				(cartoonProp
					view: 771
					cycleSpeed: (Max 0 (- 4 (* local23 2)))
					posn: 146 80
					setScript: rangeScr self 4
				)
			)
			(57 (= ticks 1))
			(58
				(cartoonProp view: 771 setScript: rangeScr self 4)
			)
			(59 (= ticks 1))
			(60
				(cartoonProp view: 771 setScript: rangeScr self 4)
			)
			(61
				(cartoonProp hide:)
				(= ticks 10)
			)
			(62
				(UnLoad 128 740 741 742 743 744)
				(= cycles 2)
			)
			(63
				(cartoonProp
					view: 775
					cel: 0
					show:
					posn: 209 100
					cycleSpeed: (Max 0 (- 9 (* local23 3)))
					setCycle: Fwd
				)
				(= ticks 300)
			)
			(64
				(cartoonProp setCycle: End self)
			)
			(65
				(cartoonProp hide:)
				(= ticks 10)
			)
			(66
				(UnLoad 128 775)
				(= cycles 2)
			)
			(67
				(Load rsVIEW 776 790)
				(= cycles 2)
			)
			(68
				(= local12 1)
				(cartoonProp
					view: 776
					posn: 105 100
					cycleSpeed: (Max 0 (- 9 (* local23 3)))
					setScript: loopRangeScr self 8
				)
			)
			(69
				(cartoonProp hide:)
				(= ticks 10)
			)
			(70
				(UnLoad 128 776)
				(= cycles 2)
			)
			(71
				(Load rsVIEW 778 779 780)
				(= cycles 2)
			)
			(72
				(= local7 1)
				(cartoonProp
					view: 778
					posn: 105 100
					cycleSpeed: (Max 0 (- 9 (* local23 3)))
					setScript: rangeScr self 3
				)
			)
			(73
				(cartoonProp hide:)
				(= ticks 10)
			)
			(74
				(UnLoad 128 778 779 780)
				(= state 54)
				(++ local23)
				(= cycles 2)
			)
			(75
				(Load rsVIEW 781 782)
				(= cycles 2)
			)
			(76
				(cartoonProp
					view: 781
					cycleSpeed: 9
					posn: (Random 15 200) (Random 10 100)
					setScript: rangeScr self 2
				)
			)
			(77
				(cartoonProp hide:)
				(= ticks 10)
			)
			(78
				(UnLoad 128 781 782)
				(= cycles 2)
			)
			(79
				(Load rsVIEW 783 784 785 786 787)
				(= cycles 2)
			)
			(80
				(cartoonProp
					view: 783
					cycleSpeed: 8
					posn: (Random 15 200) (Random 10 100)
					setScript: rangeScr self 5
				)
			)
			(81
				(cartoonProp hide:)
				(= ticks 10)
			)
			(82
				(UnLoad 128 783 784 785 786 787)
				(= cycles 2)
			)
			(83
				(Load rsVIEW 765 766 767)
				(= cycles 2)
			)
			(84
				(= local7 -1)
				(cartoonProp
					view: 767
					posn: (Random 15 200) (Random 10 112)
					setScript: rangeScr self 3
				)
			)
			(85
				(cartoonProp hide:)
				(= ticks 10)
			)
			(86
				(UnLoad 128 765 766 767)
				(= cycles 2)
			)
			(87
				(Load rsVIEW 740 741 742 743 744)
				(= cycles 2)
			)
			(88
				(= local7 1)
				(cartoonProp
					view: 740
					cycleSpeed: 9
					posn:
						(/ (- 320 (CelWide 740 0 0)) 2)
						(/ (- 190 (CelHigh 740 0 0)) 2)
					setScript: rangeScr self 5
				)
			)
			(89
				(cartoonProp hide:)
				(= ticks 10)
			)
			(90
				(UnLoad 128 740 741 742 743 744)
				(= cycles 2)
			)
			(91
				(Load rsVIEW 792 793)
				(= cycles 2)
			)
			(92
				(= local7 1)
				(cartoonProp
					view: 792
					cycleSpeed: 14
					posn:
						(/ (- 320 (CelWide 740 0 0)) 2)
						(/ (- 190 (CelHigh 740 0 0)) 2)
					init:
					setScript: rangeScr self 2
				)
			)
			(93 0)
			(94
				(= theTicks 1)
				(= local20 40)
				(= local25 1)
				(ego setScript: modifyPaletteScr self)
			)
			(95 (= cycles 2))
			(96
				(= local25 0)
				(theMusic client: self)
				(= local20 0)
				(= local21 1)
				(ego
					setScript: (modifyPaletteScr start: 1 register: 40 yourself:) self
				)
			)
			(97
				(cartoonProp dispose:)
				(thePlane drawPic: -1)
				(= cycles 2)
			)
			(98 (Palette 2 0 255 100))
			(99
				(= temp0 (ByteArray new: 100))
				(Message 0 740 0 0 3 1 (temp0 data?))
				(proc79_4 (temp0 data?) 61 0 130 80 320 900)
			)
			(100
				(proc79_5)
				(theMusic client: 0)
				(modifyPaletteScr start: 0)
				(= theTicks 1)
				(self setScript: modifyPaletteScr self)
			)
			(101 (= cycles 2))
			(102
				(Palette 2 0 255 100)
				(self dispose:)
			)
		)
	)
)

(instance fireWorksScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 90))
			(1
				(fireWorks
					show:
					view: (if (< (Random 0 100) 50) 789 else 791)
					loop: 0
					cel: 0
					setCycle: End self
				)
				(switch (if (> (++ local17) 5) (Random 1 5) else local17)
					(1 (fireWorks posn: 63 49))
					(2 (fireWorks posn: 133 33))
					(3 (fireWorks posn: 194 26))
					(4 (fireWorks posn: 243 29))
					(5 (fireWorks posn: 160 62))
				)
			)
			(2
				(fireWorks hide:)
				(if (not register)
					(= state (- state 2))
					(= ticks 40)
				else
					(fireWorks dispose:)
				)
			)
		)
	)
)

(instance playMusicScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= ticks 120))
			(1
				(= temp0 (ByteArray new: 100))
				(Message 0 740 0 0 3 2 (temp0 data?))
				(proc79_4 (temp0 data?) 77 -1 80 80 320 900)
				(if (!= (theMusic prevSignal?) -1)
					(theMusic fade: 0 25 10 1)
					(= ticks 1)
				else
					(++ state)
					(= ticks 360)
				)
			)
			(2
				(temp0 dispose:)
				(proc79_5)
				(if (!= (theMusic prevSignal?) -1)
					(-- state)
					(= ticks 30)
				else
					(= cycles 2)
				)
			)
			(3 (= cycles 2))
			(4
				(= local27 1)
				(musicManager register: 1 cue:)
				(self dispose:)
			)
		)
	)
)

(instance musicManager of Script
	(properties)
	
	(method (cue &tmp temp0)
		(if (== (theMusic prevSignal?) -1)
			(if (not register)
				(waitTimer setReal: self 5)
				(= register 1)
				(proc79_5)
			else
				(proc79_5)
				(= temp0 (ByteArray new: 110))
				(Message 0 740 0 0 22 (+ local22 2) (temp0 data?))
				(proc79_4
					(temp0 data?)
					(local3 at: (Random 0 7))
					-1
					80
					80
					320
					900
				)
				(theMusic
					number:
						(switch (++ local22)
							(0 1000)
							(1 230)
							(2 380)
							(3 440)
							(4 442)
							(5 511)
							(6 1001)
							(7 560)
							(8 860)
							(9 320)
							(10 420)
							(11 580)
							(12 430)
							(13 840)
							(14 310)
							(15 580)
							(16 (= local22 -1) 337)
						)
					play: self
				)
				(= register 0)
			)
		)
	)
)

(instance invCursor of Cursor
	(properties
		view 98
	)
)

(instance waitTimer of Timer
	(properties)
)

(instance myCreditText of Print
	(properties
		doBorder 0
	)
	
	(method (doit)
	)
	
	(method (handleEvent event)
		(if (event type?)
			(event claimed: 1)
			(theMusic pause: 1)
			(localproc_008c)
		)
	)
)

(instance musicCue of cObj
	(properties)
	
	(method (cue)
		(if
			(and
				(== (theMusic prevSignal?) -1)
				(== (curRoom script?) scrollText)
			)
			(while (== local1 local2)
				(= local2 (Random 0 17))
			)
			(= local1 local2)
			(theMusic number: [local33 local2] play: self)
		)
	)
)

(instance quitCue of cObj
	(properties)
	
	(method (cue)
		(= quit 1)
	)
)

(instance sfx of Sound
	(properties)
)
