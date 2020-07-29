;;; Sierra Script 1.0 - (do not remove this comment)
(script# 920)
(include game.sh)
(use Main)
(use LLRoom)
(use MoveCyc)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm920 0
)

(local
	[local0 25] = [0 11 91 86 0 12 92 89 0 13 91 91 0 14 111 98 0 14 153 84 0 14 173 80 -32768]
)
(instance rm920 of LLRoom
	(properties
		picture 920
	)
	
	(method (init)
		(Load PICTURE 1)
		(super init:)
		(ego
			init:
			normalize:
			view: 923
			posn: 137 117
			setCycle: 0
			setLoop: 0
			setCel: 0
			setPri: 8
			setStep: 1 1
			illegalBits: 0
			ignoreActors: TRUE
			hide:
		)
		(crack init: hide:)
		(bars init: hide:)
		(rope init: hide:)
		(chiChi init: setCycle: Forward)
		(blinds init: setCel: 255)
		(theMusic number: 920 loop: -1 play:)
		(self setScript: sCartoon)
	)
	
	(method (newRoom n)
		(theMusic stop:)
		(theMusic2 stop:)
		(super newRoom: n)
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(SetFFRoom 900)
				(InFirstPerson 0)
				(= ticks 90)
			)
			(1
				(Say ego 920 0)
				(= ticks 30)
			)
			(2
				(TimePrint 920 1 #title {Chi Chi})
				(= ticks 90)
			)
			(3 (blinds setCycle: BegLoop self))
			(4
				(blinds stopUpd:)
				(= ticks 90)
			)
			(5
				(if (!= (chiChi cel?) 1) (-- state) else (ego show:))
				(= register (ego cycleSpeed?))
				(= cycles 2)
			)
			(6
				(ego cycleSpeed: 8 setCycle: EndLoop self)
			)
			(7
				(theMusic2 number: 913 loop: 1 play:)
				(ego
					cycleSpeed: register
					setLoop: 1
					setCel: 0
					posn: 85 82
				)
				(rope show: setCycle: EndLoop)
				(Say ego 920 2 #width -1 20 #dispose)
				(ShakeScreen 5 shakeSDown)
				(= ticks 90)
			)
			(8
				(ego setStep: 3 6 setMotion: MoveTo 85 124 self)
			)
			(9 (= seconds 3))
			(10 (ego setCycle: EndLoop self))
			(11 (= seconds 4))
			(12
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(blinds setCycle: EndLoop self)
			)
			(13
				(Load VIEW 924)
				(Load VIEW 925)
				(blinds stopUpd:)
				(= seconds 4)
			)
			(14
				(ego
					hide:
					setMotion: 0
					view: 924
					cycleSpeed: 12
					setStep: 3 3
					setLoop: 0
					setCel: 0
					posn: 77 93
				)
				(chiChi
					view: 925
					setLoop: 0
					cycleSpeed: 18
					posn: 176 166
					setCycle: Forward
					setPri: 13
				)
				(bars
					show:
					view: 920
					setLoop: 2
					setCel: 1
					posn: 180 159
					stopUpd:
				)
				(rope
					view: 920
					setLoop: 2
					setCel: 0
					posn: 104 136
					stopUpd:
				)
				(blinds setCycle: BegLoop self)
			)
			(15
				(blinds stopUpd:)
				(= seconds 4)
			)
			(16
				(TimePrint 920 3 67 -1 15 70 280 80 {Chi Chi})
				(chiChi
					setLoop: 1
					setCel: 0
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(17
				(chiChi setCel: 0 setCycle: EndLoop self)
			)
			(18 (= ticks 120))
			(19
				(ego show: setCycle: CycleTo 4 1 self)
			)
			(20
				(TimePrint 920 4 67 -1 20 80 {Chi Chi})
				(ego setCycle: CycleTo 10 1 self)
			)
			(21
				(theMusic2 number: 448 loop: 1 play:)
				(ego
					setCel: 11
					cycleSpeed: 12
					setCycle: MoveCycle @local0 self
				)
			)
			(22
				(theMusic2 number: 913 loop: 1 play:)
				(Say ego 920 5 80 {You} 67 -1 20 108)
				(ego hide:)
				(crack
					show:
					view: 920
					setLoop: 1
					setCel: 0
					cycleSpeed: 6
					posn: 244 59
					setPri: 15
					setCycle: EndLoop self
				)
			)
			(23
				(ShakeScreen 7 2)
				(crack addToPic:)
				(= seconds 4)
			)
			(24
				(if modelessDialog (modelessDialog dispose:))
				(blinds setCycle: EndLoop self)
			)
			(25
				(Load VIEW 921)
				(Load VIEW 922)
				(rope dispose:)
				(bars
					setPri: 4
					posn: 131 161
					setCel: 2
					setLoop: 2
					stopUpd:
				)
				(ego show: view: 921 posn: 85 124 setLoop: 3 setCel: 0)
				(chiChi
					view: 922
					posn: 102 123
					cycleSpeed: 9
					moveSpeed: 9
					setLoop: 0
					setCel: 0
				)
				(blinds stopUpd:)
				(= seconds 4)
			)
			(26 (blinds setCycle: BegLoop self))
			(27
				(blinds stopUpd:)
				(= ticks 30)
			)
			(28
				(chiChi
					setStep: 2 2
					setCycle: Walk
					setMotion: MoveTo 150 (chiChi y?) self
				)
			)
			(29
				(chiChi
					setLoop: 1
					setCel: 0
					cycleSpeed: 12
					setCycle: CycleTo 7 1 self
				)
			)
			(30 (= ticks 30))
			(31 (chiChi setCycle: EndLoop self))
			(32 (= ticks 90))
			(33
				(TimePrint 920 6
					#title {Chi Chi}
					#at -1 15
					#width 280
				)
				(= ticks 90)
			)
			(34
				(ego cycleSpeed: 11 moveSpeed: 11 setCycle: EndLoop self)
			)
			(35 (= ticks 30))
			(36
				(ego
					setCycle: Walk
					setLoop: 0
					setStep: 1 1
					setMotion: MoveTo 116 (ego y?) self
				)
			)
			(37
				(ego setLoop: 1 setCel: 0 setCycle: CycleTo 3 1 self)
			)
			(38
				(theMusic2 number: 914 loop: 1 play:)
				(Say ego 920 7
					#title {You}
					#at -1 20
					#dispose
				)
				(ShakeScreen 5 shakeSRight)
				(= ticks 90)
			)
			(39 (ego setCycle: EndLoop self))
			(40 (= ticks 90))
			(41
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(theMusic2 number: 915 loop: 1 play:)
				(ego
					setLoop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(42 (= seconds 4))
			(43 (blinds setCycle: EndLoop self))
			(44 (= ticks 180))
			(45
				(ego hide:)
				(chiChi dispose:)
				(bars dispose:)
				(blinds dispose:)
				(DrawPic 1 IRISIN)
				(theMusic fade:)
				(theMusic2 fade:)
				(= seconds 3)
			)
			(46
				(SolvePuzzle 40 fAfterChiChi)
				(TimePrint 920 8)
				(if (>= ((Inventory at: iCamcorder) state?) 100)
					(CheckTapeState tapeCHICHI)
					(SolvePuzzle 20 fRecordedChiChi)
					(TimePrint 920 9)
					(TimePrint 920 10 #at -1 185)
				else
					(TimePrint 920 11)
				)
				(curRoom newRoom: 900)
			)
		)
	)
)

(instance chiChi of Actor
	(properties
		x 128
		y 108
		description {Chi Chi Lambada}
		sightAngle 90
		lookStr {Chi Chi loves doing sexually-explicit gymnastics routines.}
		view 922
		loop 2
		priority 7
		signal (| ignrAct fixedLoop  fixPriOn)
		cycleSpeed 8
	)
)

(instance blinds of Prop
	(properties
		x 128
		y 158
		description {the venetian blinds}
		sightAngle 90
		lookStr {If it weren't for these venetian blinds, we'd all have a perfect view!}
		view 920
		priority 14
		signal (| ignrAct fixedLoop  fixPriOn)
		cycleSpeed 10
	)
)

(instance rope of Prop
	(properties
		x 136
		y 44
		description {the rope}
		sightAngle 90
		lookStr {Hang on to this!}
		view 923
		loop 2
		signal (| ignrAct fixedLoop  fixPriOn)
		cycleSpeed 8
	)
)

(instance bars of View
	(properties
		view 920
		signal (| ignrAct fixedLoop  fixPriOn)
	)
)

(instance crack of Prop
	(properties
		view 920
		signal (| ignrAct fixedLoop  fixPriOn)
	)
)
