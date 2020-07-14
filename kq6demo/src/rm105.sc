;;; Sierra Script 1.0 - (do not remove this comment)
(script# 105)
(include game.sh) (include "105.shm")
(use Main)
(use Audio)
(use KQ6Room)
(use Sync)
(use LoadMany)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm105 0
)

(local
	local0 =  1
	[str 400]
	saveBits
	local402
)
(instance rm105 of KQ6Room
	(properties
		picture 98
		autoLoad 0
	)
	
	(method (init)
		(super init: &rest)
		(if (checkForFiles doit:)
			(Palette PALIntensity 0 256 0)
			(directionHandler addToFront: self)
			(keyDownHandler addToFront: self)
			(mouseDownHandler addToFront: self)
			(self setScript: showMovie)
		else
			(messager say: NULL NULL C_MISSING_FILES 0 self)
		)
	)
	
	(method (doit)
		(if local0 (localAudio check:))
		(super doit: &rest)
	)
	
	(method (handleEvent event)
		(if (== (event type?) keyDown)
			(switch (event message?)
				(KEY_CONTROL
					(theGame quitGame:)
					(event claimed: TRUE)
				)
				(else  (event claimed: TRUE))
			)
		)
	)
	
	(method (cue)
		(theGame restart: TRUE)
	)
	
	(method (newRoom)
		(= msgType TEXT_MSG)
		(= cDAudio FALSE)
		(directionHandler delete: self)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(LoadMany FALSE SYNC)
		(SetVideoMode 0)
		(super newRoom: &rest)
	)
)

(instance checkForFiles of Code
	(properties)
	
	(method (doit &tmp [fileBuf 20])
		(Format @fileBuf 105 0)
		(return (if (FileIO fileExists @fileBuf) (return TRUE) else (return FALSE)))
	)
)

(instance localAudio of Audio
	(properties)
)

(instance showMovie of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theIconBar disable: height: -100 activateHeight: -100)
				(User controls: TRUE)
				(Cursor showCursor: 0)
				(if (DoSound NumDACs)
					(= msgType CD_MSG)
					(= cDAudio TRUE)
				else
					(= msgType TEXT_MSG)
					(= cDAudio FALSE)
				)
				(= cycles 1)
			)
			(1
				(DrawPic 107)
				(SetVideoMode 1)
				(Animate 0)
				(= temp0 0)
				(while (< temp0 100)
					(Palette PALIntensity 0 256 temp0)
					(= temp1 0)
					(while (< temp1 30)
						(++ temp1)
					)
					(++ temp0)
				)
				(= cycles 1)
			)
			(2
				((ScriptID 107 2) doit: 1 self)
			)
			(3
				(theGlobalSound1 loop: 1 number: 105 play:)
				(Message MsgGet 105 N_MOVIE 0 0 1 @str)
				(= saveBits
					(Display @str
						p_at 12 81
						p_width 300
						p_color 98
						p_font 3110
						p_mode teJustCenter
						p_save
					)
				)
				(= cycles 1)
			)
			(4
				((ScriptID 107 2) doit: 0 self)
			)
			(5 (= seconds 6))
			(6
				((ScriptID 107 2) doit: 1 self)
			)
			(7
				(Display 105 N_MOVIE p_restore saveBits)
				(= seconds 2)
			)
			(8
				(self setScript: alAndValTalkOne self)
			)
			(9
				((ScriptID 107 2) doit: 1 self)
			)
			(10 (= seconds 2))
			(11
				(DrawPic 104)
				(flameThree init: setCycle: Forward)
				(= cycles 1)
			)
			(12
				((ScriptID 107 2) doit: 0 self)
			)
			(13 (= seconds 2))
			(14 (= seconds 2))
			(15
				(theGlobalSound1 fade:)
				(= cycles 10)
			)
			(16
				(theGlobalSound1 number: 106 play:)
				(= seconds 5)
			)
			(17
				(flameThree dispose:)
				(= cycles 1)
			)
			(18
				(theGlobalSound1 stop:)
				(theGlobalSound1 number: 107 play:)
				(ShowMovie {FS3A.SEQ} 8)
				(ShowMovie {FS3B.SEQ} 8)
				(= cycles 1)
			)
			(19
				((ScriptID 107 2) doit: 1 self)
			)
			(20 (DrawPic 141) (= cycles 1))
			(21
				((ScriptID 107 2) doit: 0 self)
			)
			(22 (= cycles 10))
			(23
				(if cDAudio
					(cassMouth init:)
					(localAudio number: 3001 play: self)
					(cassMouth setCycle: MouthSync 3001)
				else
					(= ticks 40)
				)
			)
			(24
				(if cDAudio
					(localAudio number: 3002 play: self)
					(cassMouth setCycle: MouthSync 3002)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 10 @str)
					(= saveBits
						(Display @str
							p_at 25 150
							p_width 280
							p_color 98
							p_font 1
							p_save
						)
					)
					(= ticks 380)
				)
			)
			(25
				(if cDAudio
					(localAudio number: 1007 play:)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 11 @str)
					(= saveBits
						(Display @str
							p_at 25 5
							p_width 280
							p_color 98
							p_font 1
							p_save
						)
					)
					(= ticks 104)
				)
				(cassMouth dispose:)
				((ScriptID 107 2) doit: 1 self)
			)
			(26 (DrawPic 107) (= cycles 2))
			(27
				(Palette PALIntensity 0 231 100)
				(theGlobalSound1 fade: 20 0 2 self)
			)
			(28
				(theGlobalSound1 number: Forward play:)
				(if cDAudio
					(localAudio number: 1008 play: self)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 12 @str)
					(= saveBits
						(Display @str
							p_at 25 5
							p_width 280
							p_color 98
							p_font 1
							p_save
						)
					)
					(= ticks 130)
				)
			)
			(29
				(ShowMovie {PAN.SEQ} 8)
				(= cycles 1)
			)
			(30
				(self setScript: alAndValTalkTwo self)
			)
			(31
				(DrawPic 107 PIXELDISSOLVE)
				(= cycles 2)
			)
			(32
				(theGlobalSound1 stop: number: 109 loop: -1 play: self)
				(Message MsgGet 105 N_MOVIE 0 0 21 @str)
				(= saveBits
					(Display @str
						p_at 12 81
						p_width 300
						p_color 98
						p_font 3110
						p_mode teJustCenter
						p_save
					)
				)
				((ScriptID 107 2) doit: 0 self)
			)
			(33 (= seconds 3))
			(34
				((ScriptID 107 2) doit: 1 self)
			)
			(35
				(Display 105 N_MOVIE p_restore saveBits)
				(= cycles 2)
			)
			(36
				(Palette PALIntensity 0 231 100)
			)
			(37
				(ShowMovie {BOAT.SEQ} 8)
				(ShowMovie {OPEN.SEQ} 8)
				(ShowMovie {SCAN.SEQ} 8)
				(= cycles 1)
			)
			(38
				(DrawPic 107)
				(scope
					init:
					setPri: 6
					illegalBits: 0
					ignoreActors: TRUE
					setLoop: 1
				)
				(ocean
					init:
					setPri: 4
					ignoreHorizon: TRUE
					illegalBits: 0
					ignoreActors: TRUE
					setLoop: 0
					setScript: moveOcean
				)
			)
			(39
				(ocean dispose:)
				(scope dispose:)
				(DrawPic 107)
				(= cycles 1)
			)
			(40
				(ShowMovie {LAND.SEQ} 8)
				((ScriptID 107 2) doit: 1 self)
			)
			(41
				(DrawPic 107)
				(theGlobalSound1 fade: 20 0 2 self)
			)
			(42
				(theGlobalSound1 stop: number: 110 loop: 1 play: self)
				(Message MsgGet 105 N_MOVIE 0 0 23 @str)
				(Display 105 N_MOVIE p_restore saveBits)
				(= saveBits
					(Display @str
						p_at 12 81
						p_width 300
						p_color 98
						p_font 3110
						p_mode teJustCenter
						p_save
					)
				)
				(= cycles 1)
			)
			(43
				((ScriptID 107 2) doit: 0 self)
			)
			(44 (= seconds 7))
			(45
				((ScriptID 107 2) doit: 1 self)
			)
			(46
				(Display 105 N_MOVIE p_restore saveBits)
				(Palette PALIntensity 0 231 100)
				(= cycles 2)
			)
			(47
				(DrawPic 107)
				(ShowMovie {NSHP.SEQ} 8)
				((ScriptID 107 2) doit: 1 self)
			)
			(48 (DrawPic 107))
			(49 (= local0 0) (= cycles 1))
			(50
				(= temp0 100)
				(while (> temp0 0)
					(Palette PALIntensity 0 256 temp0)
					(= temp1 0)
					(while (< temp1 30)
						(++ temp1)
					)
					(-- temp0)
				)
				(= cycles 1)
			)
			(51 (DrawPic 98) (= cycles 1))
			(52
				(SetVideoMode 0)
				(Cursor showCursor: TRUE)
				(theGame restart: TRUE)
			)
		)
	)
)

(instance alAndValTalkOne of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawPic 106)
				(alEyes106 init:)
				(valEyes106 init:)
				(alBrow106 init:)
				(valBrow106 init:)
				(alLArm106 init:)
				(alRArm106 init:)
				(valRArm106 init:)
				(valLArm106 init:)
				(flameFour init: setPri: 15 setCycle: Forward)
				(= cycles 1)
			)
			(1
				((ScriptID 107 2) doit: 0 self)
			)
			(2
				(alMouth init: setPri: 15 setCycle: Forward hide:)
				(if cDAudio
					(localAudio number: 2001 play: self)
					(valMouth init: setPri: 15 setCycle: MouthSync 2001)
				else
					(valMouth init: setPri: 15 setCycle: Forward)
					(Message MsgGet 105 N_MOVIE 0 0 2 @str)
					(= saveBits
						(Display @str
							p_at 25 150
							p_width 300
							p_color 98
							p_font teJustCenter
							p_save
						)
					)
					(= ticks 319)
				)
			)
			(3
				(alMouth show:)
				(valMouth setCycle: 0 hide:)
				(if cDAudio
					(localAudio number: 1002 play: self)
					(alMouth show: setCycle: MouthSync 1002)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 3 @str)
					(= saveBits
						(Display @str
							p_at 25 5
							p_width 280
							p_color 98
							p_font USERFONT
							p_save
						)
					)
					(= ticks 116)
				)
			)
			(4
				(alMouth setCycle: 0 hide:)
				(valMouth show: setPri: 15 setCycle: Forward)
				(if cDAudio
					(localAudio number: 2002 play: self)
					(valMouth show: setPri: 15 setCycle: MouthSync 2002)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 4 @str)
					(= saveBits
						(Display
							@str
							p_at 25 150
							p_width 300
							p_color 98
							p_font USERFONT
							p_save
						)
					)
					(= ticks 383)
				)
			)
			(5
				(valMouth setCycle: 0 hide:)
				(alMouth show: setPri: 15 setCycle: Forward)
				(if cDAudio
					(localAudio number: 1003 play: self)
					(alMouth show: setPri: 15 setCycle: MouthSync 1003)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 5 @str)
					(= saveBits
						(Display @str
							p_at 25 5
							p_width 280
							p_color 98
							p_font USERFONT
							p_save
						)
					)
					(= ticks 60)
				)
			)
			(6
				(alMouth setCycle: 0 hide:)
				(valMouth show: setPri: 15 setCycle: Forward)
				(if cDAudio
					(localAudio number: 2004 play: self)
					(valMouth show: setPri: 15 setCycle: MouthSync 2004)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 6 @str)
					(= saveBits
						(Display @str
							p_at 25 150
							p_width 280
							p_color 98
							p_font USERFONT
							p_save
						)
					)
					(= ticks 281)
				)
			)
			(7
				(valMouth setCycle: 0 hide:)
				(alMouth show: setPri: 15 setCycle: Forward)
				(if cDAudio
					(localAudio number: 1005 play: self)
					(alMouth show: setPri: 15 setCycle: MouthSync 1005)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 7 @str)
					(= saveBits
						(Display @str
							p_at 25 5
							p_width 280
							p_color 98
							p_font USERFONT
							p_save
						)
					)
					(= ticks 210)
				)
			)
			(8
				(alMouth setCycle: 0 hide:)
				(valMouth show: setPri: 15 setCycle: Forward)
				(if cDAudio
					(localAudio number: 2005 play: self)
					(valMouth show: setPri: 15 setCycle: MouthSync 2005)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 8 @str)
					(= saveBits
						(Display @str
							p_at 25 150
							p_width 280
							p_color 98
							p_font USERFONT
							p_save
						)
					)
					(= ticks 216)
				)
			)
			(9
				(valMouth setCycle: 0 hide:)
				(alMouth show: setPri: 15 setCycle: Forward)
				(if cDAudio
					(localAudio number: 1006 play: self)
					(alMouth show: setPri: 15 setCycle: MouthSync 1006)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 9 @str)
					(= saveBits
						(Display @str
							p_at 25 5
							p_width 280
							p_color 98
							p_font USERFONT
							p_save
						)
					)
					(= ticks 278)
				)
			)
			(10 (= cycles 2))
			(11
				(Display 105 N_MOVIE p_restore saveBits)
				(alBrow106 dispose:)
				(alEyes106 dispose:)
				(valBrow106 dispose:)
				(valEyes106 dispose:)
				(alLArm106 dispose:)
				(alRArm106 dispose:)
				(valRArm106 dispose:)
				(valLArm106 dispose:)
				(flameFour dispose:)
				(valMouth dispose:)
				(alMouth dispose:)
				(theIconBar disable: height: 0 activateHeight: 0)
				(self dispose:)
			)
		)
	)
)

(instance alAndValTalkTwo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawPic 106)
				(alEyes106 init:)
				(valEyes106 init:)
				(alBrow106 init:)
				(valBrow106 init:)
				(alLArm106 init:)
				(alRArm106 init:)
				(valRArm106 init:)
				(valLArm106 init:)
				(flameFour init: setPri: 15 setCycle: Forward)
				(valMouth init: setPri: 15 setCycle: Forward)
				(if cDAudio
					(localAudio number: 2006 play: self)
					(valMouth init: setPri: 15 setCycle: MouthSync 2006)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 13 @str)
					(= saveBits
						(Display @str
							p_at 25 150
							p_width 280
							p_color 98
							p_font USERFONT
							p_save
						)
					)
					(= ticks 259)
				)
			)
			(1
				(valMouth setCycle: 0 hide:)
				(alMouth init: setPri: 15 setCycle: Forward)
				(if cDAudio
					(localAudio number: 1009 play: self)
					(alMouth init: setPri: 15 setCycle: MouthSync 1009)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 14 @str)
					(= saveBits
						(Display @str
							p_at 25 5
							p_width 280
							p_color 98
							p_font teJustCenter
							p_save
						)
					)
					(= ticks 233)
				)
			)
			(2
				(alMouth setCycle: 0 hide:)
				(valMouth show: setPri: 15 setCycle: Forward)
				(if cDAudio
					(localAudio number: 2007 play: self)
					(valMouth show: setPri: 15 setCycle: MouthSync 2007)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 15 @str)
					(= saveBits
						(Display @str
							p_at 25 150
							p_width 280
							p_color 98
							p_font USERFONT
							p_save
						)
					)
					(= ticks 176)
				)
			)
			(3
				(valMouth setCycle: 0 hide:)
				(alMouth show: setPri: 15 setCycle: Forward)
				(if cDAudio
					(localAudio number: 1010 play: self)
					(alMouth show: setPri: 15 setCycle: MouthSync 1010)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 16 @str)
					(= saveBits
						(Display @str
							p_at 25 5
							p_width 280
							p_color 98
							p_font USERFONT
							p_save
						)
					)
					(= ticks 94)
				)
			)
			(4
				(alMouth setCycle: 0 hide:)
				(valMouth show: setPri: 15 setCycle: Forward)
				(if cDAudio
					(localAudio number: 2008 play: self)
					(valMouth show: setPri: 15 setCycle: MouthSync 2008)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 17 @str)
					(= saveBits
						(Display @str
							p_at 25 150
							p_width 280
							p_color 98
							p_font USERFONT
							p_save
						)
					)
					(= ticks 71)
				)
			)
			(5
				(valMouth setCycle: 0 hide:)
				(alMouth show: setPri: 15 setCycle: Forward)
				(if cDAudio
					(localAudio number: 1011 play: self)
					(alMouth show: setPri: 15 setCycle: MouthSync 1011)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 18 @str)
					(= saveBits
						(Display @str
							p_at 25 5
							p_width 280
							p_color 98
							p_font USERFONT
							p_save
						)
					)
					(= ticks 351)
				)
			)
			(6
				(alMouth setCycle: 0 hide:)
				(valMouth show: setPri: 15 setCycle: Forward)
				(if cDAudio
					(localAudio number: 2009 play: self)
					(valMouth show: setPri: 15 setCycle: MouthSync 2009)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 19 @str)
					(= saveBits
						(Display @str
							p_at 25 150
							p_width 280
							p_color 98
							p_font USERFONT
							p_save
						)
					)
					(= ticks 245)
				)
			)
			(7
				(valMouth setCycle: 0 hide:)
				(alMouth show: setPri: 15 setCycle: Forward)
				(if cDAudio
					(localAudio number: 1012 play: self)
					(alMouth show: setPri: 15 setCycle: MouthSync 1012)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 20 @str)
					(= saveBits
						(Display @str
							p_at 25 5
							p_width 280
							p_color 98
							p_font teJustCenter
							p_save
						)
					)
					(= ticks 170)
				)
			)
			(8
				(Display 105 N_MOVIE p_restore saveBits)
				((ScriptID 107 2) doit: 1 self)
			)
			(9
				(alEyes106 dispose:)
				(valEyes106 dispose:)
				(alBrow106 dispose:)
				(valBrow106 dispose:)
				(alLArm106 dispose:)
				(alRArm106 dispose:)
				(valRArm106 dispose:)
				(valLArm106 dispose:)
				(flameFour dispose:)
				(valMouth dispose:)
				(alMouth dispose:)
				(theIconBar disable: height: 0 activateHeight: 0)
				(self dispose:)
			)
		)
	)
)

(instance moveOcean of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ocean setMotion: MoveTo -17 36)
				(= cycles 9)
			)
			(1
				(ocean setMotion: MoveTo 300 36)
				(= cycles 5)
			)
			(2
				(ocean setMotion: MoveTo -17 36)
				(= cycles 7)
			)
			(3
				(ocean posn: (ocean x?) (+ (ocean y?) 1))
				(= cycles 2)
			)
			(4
				(ocean posn: (ocean x?) (+ (ocean y?) 1))
				(= cycles 9)
			)
			(5
				(ocean posn: (ocean x?) (- (ocean y?) 1))
				(= cycles 2)
			)
			(6
				(ocean posn: (ocean x?) (- (ocean y?) 1))
				(= cycles 9)
			)
			(7
				(ocean setMotion: MoveTo 300 36)
				(= cycles 4)
			)
			(8
				(ocean posn: (ocean x?) (- (ocean y?) 1))
				(= cycles 2)
			)
			(9
				(ocean posn: (ocean x?) (- (ocean y?) 1))
				(= cycles 9)
			)
			(10
				(ocean setMotion: MoveTo -17 36)
				(= cycles 6)
			)
			(11
				(ocean posn: (ocean x?) (+ (ocean y?) 1))
				(= cycles 2)
			)
			(12
				(ocean posn: (ocean x?) (+ (ocean y?) 1))
				(= seconds 6)
			)
			(13
				(if cDAudio
					(localAudio number: 1013 play: self)
				else
					(Display 105 N_MOVIE p_restore saveBits)
					(Message MsgGet 105 N_MOVIE 0 0 22 @str)
					(= saveBits
						(Display @str
							p_at 25 5
							p_width 280
							p_color 98
							p_font USERFONT
							p_save
						)
					)
					(= ticks 254)
				)
			)
			(14
				(ocean setMotion: MoveTo -17 39 showMovie)
			)
		)
	)
)

(class EyesAndBrows of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
	)
	
	(method (init)
		(self setScript: (motionScript new:))
		(super init: &rest)
	)
)

(instance motionScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 5)))
			(1 (client setCycle: EndLoop self))
			(2 (= cycles (Random 5 7)))
			(3 (client setCycle: BegLoop self))
			(4 (self init:))
		)
	)
)

(instance cassEyes of EyesAndBrows
	(properties
		x 103
		y 69
		view 140
		cel 1
	)
)

(instance alEyes106 of EyesAndBrows
	(properties
		x 97
		y 74
		view 109
		loop 3
	)
)

(instance valEyes106 of EyesAndBrows
	(properties
		x 225
		y 73
		view 109
		loop 7
		cel 1
	)
)

(instance alBrow106 of EyesAndBrows
	(properties
		x 98
		y 71
		view 109
		loop 2
	)
)

(instance valBrow106 of EyesAndBrows
	(properties
		x 227
		y 70
		view 109
		loop 6
	)
)

(instance cassMouth of Actor
	(properties
		x 111
		y 86
		view 1091
		cel 7
	)
)

(instance valMouth of Actor
	(properties
		x 242
		y 70
		view 106
		cel 2
	)
)

(instance alMouth of Actor
	(properties
		x 71
		y 65
		view 106
		loop 1
		cel 6
	)
)

(class Arms of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
	)
	
	(method (init)
		(self setScript: (armScript new:))
		(super init: &rest)
	)
)

(instance armScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 5)))
			(1
				(switch client
					(valRArm106
						(if (valMouth cycler?)
							(client setCycle: EndLoop self)
						else
							(self init:)
						)
					)
					(alRArm106
						(if (alMouth cycler?)
							(alLArm106 setCycle: EndLoop self)
						else
							(self init:)
						)
					)
				)
			)
			(2
				(if (== client valRArm106)
					(= seconds 10)
				else
					(= cycles 10)
				)
			)
			(3 (client setCycle: BegLoop self))
			(4 (self init:))
		)
	)
)

(instance valRArm106 of Arms
	(properties
		x 230
		y 97
		view 109
		loop 4
	)
	
	(method (setCycle theCycler)
		(valLArm106 setCycle: theCycler)
		(super setCycle: theCycler &rest)
	)
)

(instance valLArm106 of Prop
	(properties
		x 288
		y 142
		view 109
		loop 5
		cel 2
	)
)

(instance cassArms of Arms
	(properties
		x 85
		y 151
		view 1401
	)
)

(instance alRArm106 of Arms
	(properties
		x 38
		y 141
		view 109
		loop 1
	)
	
	(method (setCycle theCycler)
		(alLArm106 setCycle: theCycler)
		(super setCycle: theCycler &rest)
	)
)

(instance alLArm106 of Prop
	(properties
		x 110
		y 141
		view 109
	)
)

(instance flameOne of Actor
	(properties
		x 209
		y 59
		view 102
		cel 5
	)
)

(instance flameTwo of Actor
	(properties
		x 208
		y 70
		view 103
		loop 1
		cel 2
	)
)

(instance flameThree of Actor
	(properties
		x 208
		y 70
		view 104
		cel 5
	)
)

(instance flameFour of Prop
	(properties
		x 176
		y 87
		view 106
		loop 2
		cel 2
	)
)

(instance scope of Actor
	(properties
		x 204
		y 128
		view 107
		loop 1
		priority 6
		illegalBits $0000
	)
)

(instance ocean of Actor
	(properties
		x 190
		y 36
		view 107
		priority 4
		illegalBits $0000
	)
)
