;;; Sierra Script 1.0 - (do not remove this comment)
(script# TIME)
(include game.sh) (include "7.shm") (include "28.shm")
(use Main)
(use GloryWindow)
(use EgoDead)
(use IconBar)
(use GControl)
(use System)

(public
	SleepChoice 0
	doSleep 2
	showTime 3
	fixTime 4
	egoRests 5
	advanceTime 6
	egoSleeps 7
)

(local
	local0
	restControls
	restRet
	local3
)
(procedure (SleepChoice &tmp evt temp1 temp2 temp3 temp4 oldCur)
	;(= temp4 (sleepIcon cel: 0))
	(temp4 nsLeft: 40 nsTop: (if Night 96 else 79) cursor: 5)
	(restControls add: temp4)
	(restControls init: show: dispose:)
	(= restControls 0)
	(switch restRet
		(1
			(RestCheck 10)
		)
		(2
			(RestCheck 30)
		)
		(3
			(RestCheck 60)
		)
		(4
			(if
				(OneOf curRoomNum
					150 160 170 180 310 390 400
					430 440 600 700 770 780 820
				)
				((= evt (Event new:)) type: mouseDown message: V_SLEEP)
				(= temp3 0)
				(if (not (mouseDownHandler handleEvent: evt))
					(regions handleEvent: evt)
				)
				(evt dispose:)
				(= temp2 1)
			else
				(egoSleeps init: 5 0)
			)
		)
		(5 (= temp2 1))
	)
	(if temp3
		(theGame setCursor: oldCur)
	)
	(return temp2)
)

(procedure (RestCheck theMin &tmp temp0 evt)
	(if
		(OneOf curRoomNum
			230 400 460 510 520 530 700
			810 820 830 851 852 853 854
		)
		(= global455 theMin)
		((= evt (Event new:)) type: mouseDown message: V_REST)
		(if (not (mouseDownHandler handleEvent: evt))
			(regions handleEvent: evt)
		)
		(evt dispose:)
	else
		(egoRests init: theMin 1)
	)
)

(instance doSleep of Code
	
	(method (init &tmp evt oldCur temp2 temp3 temp4 [str 60])
		(= temp3 1)
		(= temp2 0)
		(= oldCur (theGame setCursor: ARROW_CURSOR))
		(if (OneOf curRoomNum 150 160 170 180)
			(= local3 40)
			((= restControls (GameControls new:))
				window:
					((GloryWindow new:)
						top: 40
						left: 65
						bottom: 80
						right: 256
						priority: 15
						yourself:
					)
			)
			((= temp4 (sleepIcon new: 1 0 1 1))
				view: 935
				loop: 1
				cel: 0
				nsTop: 2
				nsLeft: 2
				modifiers: 1
			)
			(restControls add: temp4)
			((= temp4 (sleepIcon new: 1 0 0 1))
				nsTop: 25
				nsLeft: 25
				cursor: 1
			)
			(restControls add: temp4)
			((= temp4 (sleepIcon new: 1 0 0 2))
				nsTop: 25
				nsLeft: 105
				cursor: 2
			)
			(restControls add: temp4)
			(restControls init: show: dispose:)
			(= restControls 0)
			(switch restRet
				(1
					((= evt (Event new:)) type: mouseDown message: V_SLEEP)
					(= temp3 0)
					(if (not (mouseDownHandler handleEvent: evt))
						(regions handleEvent: evt)
					)
					(evt dispose:)
					(= temp2 1)
				)
				(2 (= temp2 0))
			)
		else
			(= local3 120)
			((= restControls (GameControls new:))
				window:
					((GloryWindow new:)
						top: 40
						left: 65
						bottom: 155
						right: 256
						priority: 15
						yourself:
					)
			)
			((= temp4 (sleepIcon new: 2 0 1 1))
				view: 935
				loop: 1
				cel: 0
				nsTop: 2
				nsLeft: 2
				modifiers: 1
			)
			(restControls add: temp4)
			((= temp4 (sleepIcon new: 2 0 0 1))
				nsLeft: 40
				nsTop: 28
				cursor: 1
			)
			(restControls add: temp4)
			((= temp4 (sleepIcon new: 2 0 0 2))
				nsLeft: 40
				nsTop: 45
				cursor: 2
			)
			(restControls add: temp4)
			((= temp4 (sleepIcon new: 2 0 0 3))
				nsLeft: 40
				nsTop: 62
				cursor: 3
			)
			(restControls add: temp4)
			(if Night
				((= temp4 (sleepIcon new: 2 0 0 4))
					nsLeft: 40
					nsTop: 79
					cursor: 4
				)
				(restControls add: temp4)
			else
				((restControls window?) bottom: 138)
			)
			(= temp4 (sleepIcon new: 2 0 0 5))
			(temp4 nsLeft: 40 nsTop: (if Night 96 else 79) cursor: 5)
			(restControls add: temp4)
			(restControls init: show: dispose:)
			(= restControls 0)
			(switch restRet
				(1
					(RestCheck 10)
				)
				(2
					(RestCheck 30)
				)
				(3
					(RestCheck 60)
				)
				(4
					(if
						(OneOf curRoomNum
							150 160 170 180 310 390 400
							430 440 600 700 770 780 820
						)
						((= evt (Event new:)) type: mouseDown message: V_SLEEP)
						(= temp3 0)
						(if (not (mouseDownHandler handleEvent: evt))
							(regions handleEvent: evt)
						)
						(evt dispose:)
						(= temp2 1)
					else
						(egoSleeps init: 5 0)
					)
				)
				(5 (= temp2 1))
			)
		)
		(if temp3 (theGame setCursor: oldCur))
		(return temp2)
	)
)

(instance egoSleeps of Code

	(method (init theHour theMin &tmp sleptHours oldTime [str 60])
		(if
			(OneOf curRoomNum
				150 160 170 180 310 390 400
				430 440 600 700 770 780 820
			)
			(= lostSleep 0)
			(= oldTime Clock)
			(switch argc
				(0
					(fixTime init: 5)
				)
				(1
					(fixTime init: theHour)
				)
				(else 
					(fixTime init: theHour theMin)
				)
			)
			(= sleptHours
				(/ (mod (- (+ Clock GAMEDAY) oldTime) GAMEDAY) GAMEHOUR)
			)
			(= [egoStats STAMINA] (ego maxStamina:))
			(if
				(and
					(not
						(ego
							takeDamage: (- (/ (* sleptHours (ego maxHealth:)) HEALTH))
						)
					)
					(Btst fPoisoned)
				)
				(EgoDead C_DEATH_POISON C_POISONED)
				(return)
			)
			(ego useMana: (- (/ (* sleptHours (ego maxMana:)) MANA)))
			(if (and (> oldTime Clock) (Btst fFlag31))
				(NextDay)
			)
			(if (< sleptHours 2)
				(messager say: NULL NULL C_SLEEP_LATE 1 0 TIME)
			else
				(messager say: NULL NULL C_AWAKEN 1 0 TIME)
			)
		else
			(messager say: N_CANT_SLEEP_HERE V_DOIT C_NO_SLEEP_HERE 0 0 TIME)
		)
	)
)

(instance showTime of Code
	
	(method (init &tmp whatDay evt oldTime temp3 temp4 [str 30] [str2 7])
		(= whatDay Day)
		(Message MsgGet TIME NULL NULL C_SHOW_TIME 1 @str)
		(Format @str2 {%d} (+ Day 1))
		(if (or (!= timeODay TIME_MIDNIGHT) (> Clock 500))
			(++ whatDay)
		)
		(if (>= (= oldTime Clock) 2750)
			(= oldTime (- oldTime GAMEDAY))
		)
		(if (< oldTime 790) (= oldTime (+ oldTime 1800)))
		(= temp3 (/ (= oldTime (- oldTime 1800)) 16))
		(= temp4 (+ (= temp4 (Abs (/ oldTime 24))) 3))
		(= temp3 (+ temp3 71))
		(= local0
			((GloryWindow new:)
				top: 0
				left: 84
				bottom: 74
				right: 240
				priority: 15
				open:
				yourself:
			)
		)
		(Display @str
			p_color 17
			p_at 0 66
			p_width 156
			p_font 999
		)
		(Display @str2
			p_color 17
			p_at 40 66
			p_width 156
			p_font 999
		)
		(if (not Night)
			(DrawCel 938 1 0 0 3)
			(DrawCel 938 2 0 temp3 temp4)
			(DrawCel 938 0 0 0 3)
		else
			(DrawCel 938 4 0 0 3)
			(DrawCel 938 5 0 temp3 temp4)
			(DrawCel 938 3 0 0 3)
		)
		(repeat
			(if
				(or
					(and
						(== ((= evt (Event new:)) type?) keyDown)
						(OneOf (evt message?) ENTER ESC)
					)
					(== (evt type?) mouseDown)
					(== (evt type?) 256)
				)
				(break)
			)
			(evt dispose:)
		)
		(evt dispose:)
		(local0 dispose:)
		(self dispose:)
	)
)

(instance fixTime of Code
	
	(method (init theHour theMin &tmp oldTime oldTimeODay)
		(= oldTime Clock)
		(if (>= argc 1)
			(= Clock (* GAMEHOUR theHour))
			(= oldSysTime (GetTime SYSTIME1))
			(if (>= argc 2)
				(= Clock (+ Clock (/ (* GAMEHOUR theMin) 60)))
			)
		)
		(= oldTimeODay timeODay)
		(cond 
			((< (^ Clock 1) 300)
				(= timeODay TIME_MIDNIGHT)
			)
			((< (^ Clock 1) 750)
				(= timeODay TIME_NOTYETDAWN)
			)
			((< (^ Clock 1) 1200)
				(= timeODay TIME_DAWN)
			)
			((< (^ Clock 1) 1650)
				(= timeODay TIME_MIDMORNING)
			)
			((< (^ Clock 1) 2100)
				(= timeODay TIME_MIDDAY)
			)
			((< (^ Clock 1) 2550)
				(= timeODay TIME_MIDAFTERNOON)
			)
			((< (^ Clock 1) 3000)
				(= timeODay TIME_SUNSET)
			)
			((< (^ Clock 1) 3450)
				(= timeODay TIME_NIGHT)
			)
			(else
				(= timeODay TIME_MIDNIGHT)
			)
		)
		(if (< Clock oldTime)
			(NextDay)
		)
		(if (and (== timeODay TIME_MIDNIGHT) (!= oldTimeODay TIME_MIDNIGHT))
			(if (== (++ lostSleep) 1)
				(messager say: NULL NULL C_TIRED 1 0 TIME)
			else
				(messager say: NULL NULL C_EXHAUSTED 1 0 TIME)
			)
		)
	)
)

(instance egoRests of Code
	
	(method (init theMin mess)
		(if
			(and
				(<= Day lastRestDay)
				(<= Clock (+ lastRestTime GAMEHOUR))
				(> [egoStats STAMINA] (/ (ego maxStamina:) 2))
			)
			(messager say: NULL NULL C_IMPATIENT 1 0 TIME)
		else
			(= lastRestDay Day)
			(= lastRestTime Clock)
			(ego
				useStamina: (- theMin)
				useMana: (- (/ theMin 5))
				takeDamage: (- (/ (+ theMin 5) 15))
			)
			(if mess
				(if (Btst fPoisoned)
					(messager say: NULL NULL C_REST_WORSE 1 0 TIME)
				else
					(messager say: NULL NULL C_REST_BETTER 1 0 TIME)
				)
			)
			(advanceTime init: 0 theMin)
		)
	)
)

(instance advanceTime of Code
	
	(method (init addHours addMinutes &tmp newTIme)
		(switch argc
			(1
				(= newTIme (+ Clock (* GAMEHOUR addHours)))
			)
			(2
				(= newTIme
					(+ Clock (* GAMEHOUR addHours) (/ (* GAMEHOUR addMinutes) 60))
				)
			)
		)
		(= newTIme (^ newTIme 1))
		(if
			(or
				(and (< Clock 1100) (> newTIme 1200))
				(and
					(< Clock 2500)
					(or (> newTIme 2600) (< newTIme Clock))
				)
			)
			(ego eatMeal:)
		)
		(while (>= newTIme GAMEDAY)
			(= newTIme (- newTIme GAMEDAY))
			(++ Day)
		)
		(fixTime
			init: (/ newTIme GAMEHOUR) (/ (* (mod newTIme GAMEHOUR) 60) GAMEHOUR)
		)
	)
)

(instance sleepIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
	)
	
	(method (new param1 param2 param3 param4 &tmp temp0 temp1 temp2)
		(= temp0 (Clone self))
		(if argc
			(= temp1 (Message MsgSize 7 param1 param2 param3 param4))
			(temp0 message: (Memory MNewPtr temp1))
			(Message MsgGet TIME param1 param2 param3 param4 (temp0 message?))
		)
		(return temp0)
	)
	
	(method (dispose)
		(Memory MDisposePtr message)
		(super dispose:)
	)
	
	(method (show)
		(= nsRight
			(+ nsLeft (if (== loop 1) 0 else (+ local3 20)))
		)
		(= nsBottom (if (== loop 1) nsTop else (+ nsTop 15)))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display message
			p_at (if (== loop 1) nsLeft else (+ nsLeft 20)) (if (== loop 1) (+ nsTop 2) else nsTop)
			p_font 123
			p_color 17
			p_mode modifiers
			p_width (if (== loop 1) 189 else local3)
		)
	)
	
	(method (select)
		(return
			(if (!= loop 1)
				(= restRet cursor)
				(restControls state: (& (restControls state?) $ffdf))
			else
				(return 0)
			)
		)
	)
	
	(method (highlight param1 &tmp temp0)
		(if (!= loop 1)
			(if param1
				(DrawCel view loop 1 nsLeft nsTop -1)
				(= temp0 46)
			else
				(DrawCel view loop 0 nsLeft nsTop -1)
				(= temp0 17)
			)
			(Display message
				p_at (if (== loop 1) nsLeft else (+ nsLeft 20)) nsTop
				p_font 123
				p_color temp0
				p_mode modifiers
				p_width (if (== loop 1) 189 else local3)
			)
		)
	)
)
