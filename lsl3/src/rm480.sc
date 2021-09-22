;;; Sierra Script 1.0 - (do not remove this comment)
(script# 480)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use Menu)
(use Actor)
(use System)

(public
	rm480 0
)

(local
	local0
	[str 200]
)
(procedure (PattiSays &tmp seconds)
	(Print @str
		#at 10 10
		#title {Patti says...}
		#width 140
		#mode teJustCenter
		#time (= seconds (PrintDelay @str))
		#dispose
	)
	(return (+ 3 seconds))
)

(procedure (YouSay &tmp seconds)
	(Print @str
		#at 160 10
		#title {You say...}
		#width 140
		#mode teJustCenter
		#time (= seconds (PrintDelay @str))
		#dispose
	)
	(return (+ 3 seconds))
)

(instance aWine of View
	(properties
		y 115
		x 162
		view 480
		loop 4
		cel 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 setCel: 3 ignoreActors: stopUpd:)
	)
)

(instance aDoor of Prop
	(properties
		y 65
		x 159
		view 480
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 3 ignoreActors: stopUpd:)
	)
)

(instance aPanties of View
	(properties
		y 131
		x 63
		view 480
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance aBra of View
	(properties
		y 131
		x 63
		view 480
		loop 1
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance aPantyhose of View
	(properties
		y 141
		x 63
		view 480
		loop 1
		cel 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance aDress of Prop
	(properties
		y 107
		x 39
		view 480
		loop 2
	)
	
	(method (init)
		(super init:)
		(self
			setPri: 11
			setCel: (if (InRoom iDress 484) 0 else 255)
			ignoreActors:
			stopUpd:
		)
	)
)

(instance atpTelescope of PicView
	(properties
		y 50
		x 160
		view 480
		loop 3
		priority 1
		signal $4000
	)
)

(instance rm480 of Room
	(properties
		picture 480
		south 470
	)
	
	(method (init)
		(music fade:)
		(Load SCRIPT REVERSE)
		(if (ego has: iWineBottle)
			(Load PICTURE 99)
			(Load VIEW 481)
			(Load SOUND 480)
			(Load SOUND 481)
			(Load SOUND 483)
		else
			(Load SOUND 9)
		)
		(super init:)
		(aWine init:)
		(aDoor init:)
		(aPanties init:)
		(aBra init:)
		(aPantyhose init:)
		(aDress init:)
		(addToPics add: atpTelescope doit:)
		(self setScript: RoomScript)
		(aPatti init:)
		(NormalEgo)
		(HandsOff)
		(ego posn: 159 188 loop: 3 observeControl: cYELLOW init:)
		(if (ego has: iWineBottle)
			(RoomScript changeState: 1)
		else
			(RoomScript changeState: 43)
		)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (>= state 10)
			(theGame setSpeed: 6)
		)
	)
	
	(method (changeState newState &tmp egoX egoY)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0)
			(1
				(= seconds 3)
			)
			(2
				(if (Btst fGaveWineToPatti)
					(Print 480 14)
					else (Print 480 15)
				)
				(= seconds 3)
			)
			(3
				(if (Btst fGaveWineToPatti)
					(Print 480 16)
				else
					(Bset fGaveWineToPatti)
					(Print 480 17)
				)
				(= currentStatus egoNORMAL)
				(NormalEgo)
				(ego observeControl: cYELLOW)
			)
			(4
				(HandsOff)
				(Ok)
				(theGame changeScore: 500)
				(Print 480 18)
				(self cue:)
			)
			(5
				(cond 
					((>= (ego y?) 140)
						(= egoX 158)
						(= egoY 140)
					)
					((& (ego onControl: origin) cLCYAN)
						(= egoX (ego x?))
						(= egoY 140)
						(-- state)
					)
					((& (ego onControl: origin) cLRED)
						(= egoX (ego x?))
						(= egoY 140)
						(-- state)
					)
					((& (ego onControl: origin) cLBLUE)
						(= egoX 95)
						(if (> (ego x?) 95)
							(= egoY (ego y?))
						else
							(= egoY 120)
						)
						(-- state)
					)
					((& (ego onControl: origin) cLGREEN)
						(= egoX 231)
						(if (< (ego x?) 231)
							(= egoY (ego y?))
						else
							(= egoY 120)
						)
						(-- state)
					)
					(else
						(Print 480 19)
					)
				)
				(ego setMotion: MoveTo egoX egoY self)
			)
			(6
				(PutInRoom iWineBottle 484)
				((Inventory at: iWineBottle) view: 28)
				(Format ((Inventory at: iWineBottle) name?) 480 20)
				(ego loop: 3)
				(aWine setCel: 4 stopUpd:)
				(= cycles 11)
			)
			(7
				(ego setMotion: MoveTo 194 140 self)
			)
			(8
				(ego setMotion: MoveTo 194 132 self)
			)
			(9
				(= saveSpeed (theGame setSpeed: 6))
				(music number: 480 loop: -1 play:)
				(StatusLine disable:)
				(TheMenuBar hide: state: FALSE)
				(Bset fCursorHidden)
				(Bset fAutoSaveDisabled)
				(Format @str 480 21)
				(= seconds (YouSay))
				(ego loop: 2)
				(Display 480 22
					p_at 0 180
					p_color vRED
					p_font userFont
				)
			)
			(10
				(ego
					posn: 176 92
					cycleSpeed: 1
					view: 481
					setPri: 8
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(11
				(Format @str 480 23)
				(= seconds (PattiSays))
			)
			(12
				(Format @str 480 24)
				(= seconds (YouSay))
			)
			(13
				(Format @str 480 25)
				(= seconds (PattiSays))
			)
			(14
				(ego loop: 1 cel: 0 setCycle: CycleTo 7 1 self)
			)
			(15
				(aWine setCel: 3 stopUpd:)
				(ego setCycle: EndLoop self)
			)
			(16
				(Format @str 480 26)
				(= seconds (YouSay))
				(ego loop: 2 cel: 0 setCycle: Forward)
			)
			(17
				(Format @str 480 27)
				(= seconds (PattiSays))
				(ego loop: 3 cel: 0 setCycle: Forward)
			)
			(18
				(aPatti setCycle: EndLoop)
				(ego loop: 4 cel: 0 setCycle: CycleTo 5 1 self)
			)
			(19
				(aWine setCel: 2 stopUpd:)
				(ego setCycle: EndLoop self)
			)
			(20
				(aPatti loop: 1 cel: 0)
				(aWine setCel: 1 stopUpd:)
				(ego loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(21
				(aPatti setCycle: EndLoop)
				(ego view: 483 loop: 0 cel: 0 setCycle: EndLoop)
				(= cycles 22)
			)
			(22
				(aPatti setCycle: BegLoop)
				(ego setCycle: BegLoop)
				(= cycles 22)
				(if (> 3 (++ local0)) (= state (- state 2)))
			)
			(23
				(Format @str 480 28)
				(= seconds (- (YouSay) 1))
			)
			(24
				(Format @str 480 29)
				(= seconds (PattiSays))
				(ego hide:)
				(aPatti loop: 2 cel: 0 setCycle: EndLoop)
				(music number: 481 loop: 2 play:)
			)
			(25
				(aPatti setCycle: CycleTo 3 -1)
				(= cycles 22)
			)
			(26
				(Format @str 480 30)
				(= seconds (YouSay))
			)
			(27
				(Format @str 480 31)
				(= seconds (PattiSays))
			)
			(28
				(aPatti setCycle: EndLoop)
				(= cycles 44)
			)
			(29
				(aPatti setCycle: CycleTo 3 -1)
				(= cycles 8)
			)
			(30
				(Format @str 480 32)
				(= seconds (PattiSays))
			)
			(31
				(Format @str 480 33)
				(= seconds (YouSay))
			)
			(32
				(aPatti loop: 3 cel: 0 setCycle: EndLoop)
				(= cycles 33)
			)
			(33
				(curRoom drawPic: 99 6)
				(cast eachElementDo: #hide)
				(systemWindow color: vLRED back: vGREY)
				(music number: 483 loop: 2 play:)
				(= cycles 30)
			)
			(34
				(Format @str 480 34)
				(= seconds (PattiSays))
			)
			(35
				(Format @str 480 35)
				(= seconds (PattiSays))
			)
			(36
				(Format @str 480 36)
				(= seconds (- (PattiSays) 1))
			)
			(37
				(Format @str 480 37)
				(= seconds (- (PattiSays) 1))
			)
			(38
				(Format @str 480 38)
				(= seconds (- (PattiSays) 1))
			)
			(39
				(Format @str 480 39)
				(= seconds (- (PattiSays) 2))
			)
			(40
				(Format @str 480 40)
				(= seconds (- (PattiSays) 2))
			)
			(41
				(Format @str 480 41)
				(Print @str #time (PrintDelay @str))
				(Format @str 480 42)
				(Print @str #time (PrintDelay @str))
				(= seconds (+ 3 (PrintDelay @str)))
			)
			(42
				(curRoom newRoom: 481)
			)
			(43
				(ego setMotion: MoveTo (ego x?) 180 self)
			)
			(44
				(Print 480 43 #at 10 5 #width 290)
				(Print 480 44 #at -1 144)
				(= seconds 3)
			)
			(45
				(Print 480 45 #at 10 5 #width 290)
				(music number: 9 loop: 1 play: self)
				(Printf 480 46 expletive)
			)
			(46
				(ego setMotion: MoveTo (ego x?) 192)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) keyDown)
				(== (event claimed?) FALSE)
				(== (event message?) `#8)
				(>= state 9)
			)
			(Print 480 0)
			(Bset fSkippedLoveScene)
			(curRoom newRoom: 484)
		)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			(
				(or
					(Said 'make/enjoy')
					(Said 'bang')
					(Said 'go,(climb<in,on),(get<in)/bed')
					(Said 'naked')
					(Said 'get/naked')
					(Said 'drain,give,backdrop/beer')
					(Said 'wear/nothing')
				)
				(if
					(and
						(not (& (ego onControl: origin) cLBLUE))
						(not (& (ego onControl: origin) cLGREEN))
						(not (& (ego onControl: origin) cLCYAN))
						(not (& (ego onControl: origin) cLRED))
					)
					(NotClose)
				else
					(self changeState: 4)
				)
			)
			((Said 'address')
				(Print 480 1)
			)
			((Said 'open/door')
				(Print 480 2)
			)
			((Said 'get')
				(Print 480 3)
			)
			((Said 'look>')
				(cond 
					((Said '/balcony,camp,bay,air,cup')
						(Print 480 4)
					)
					((Said '/bed')
						(Print 480 5)
					)
					((Said '/entertainer,babe')
						(Print 480 6)
					)
					((Said '/cloth')
						(Print 480 7)
					)
					((Said '/keyboard')
						(Print 480 8)
					)
					((Said '/binocular')
						(Print 480 9)
					)
					((Said '/burn')
						(Print 480 10)
					)
					((Said '/bush')
						(Print 480 11)
					)
					((Said '/buffet')
						(Print 480 12)
					)
					((Said '[/area]')
						(Print 480 13)
					)
				)
			)
		)
	)
)

(instance aPatti of Prop
	(properties
		y 101
		x 151
		view 483
		loop 4
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 8 stopUpd:)
	)
)
