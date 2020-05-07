;;; Sierra Script 1.0 - (do not remove this comment)
(script# 340)
(include game.sh)
(use Main)
(use Intrface)
(use dagNabItHelp)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	dagNabIt 0
)

(local
	newView_3
	newView_4
	forceMenu
	newAct
	newAct_2
	egoNamePlate
	chiefNamePlate
	local7
	local8
	local9
	local10
	local11
	local12
	local13
	egoScore
	chiefScore
	local16
	local17
	local18
	bet =  5
	local20
	local21 =  5
	local22 =  7
	[newView_5 12]
	local35
	theGNewSpeed
	[scoreStr 50]
)
(procedure (localproc_000c param1 param2 param3 param4 param5 param6)
	(return
		(if
			(and
				(<= param3 param1)
				(< param1 param5)
				(<= param4 param2)
			)
			(< param2 param6)
		else
			0
		)
	)
)

(procedure (DisplayDartScore pts fg x y)
	(Display
		(Format @scoreStr 340 0 pts)
		p_at x y
		p_mode teJustLeft
		p_font ARROW_CURSOR 
		p_color fg
		p_back vBLACK
		p_width 18
	)
)

(procedure (localproc_0059)
	(= local8 (Random 40 100))
	(= local9 (Random 85 100))
)

(procedure (localproc_006e)
	(while (>= (-- local35) 0)
		([newView_5 local35] dispose:)
	)
	(= local35 0)
)

(procedure (localproc_0088 &tmp temp0 temp1)
	(= local10 (+ local8 (* 6 (- (newView_4 cel?) 7))))
	(= local11 (+ local9 (* -8 (newView_3 cel?))))
	(= temp0 (/ (- 100 [egoStats LUCK]) 4))
	(TrySkill LUCK 0 0)
	(if (and (TrySkill THROW 0 0) (> temp0 6)) (= temp0 6))
	(= local10
		(+ local10 (- (= temp1 (Random 0 temp0)) (/ temp0 2)))
	)
	(if temp0
		(= local11
			(+ local11 (- (Random 0 temp0) (/ temp0 2)))
		)
	)
)

(procedure (localproc_0107 &tmp temp0 temp1)
	(if
		(<
			(= temp0 (- local20 (+ dartsBonus (* local16 3))))
			6
		)
		(= temp0 6)
	)
	(if (>= totalDagNabItBet 100) (= temp0 (Random 2 5)))
	(= local10 (+ 70 (- (= temp1 (Random 0 temp0)) (/ temp0 2))))
	(= temp0 (- temp0 temp1))
	(= local11 (+ 45 (- (Random 0 temp0) (/ temp0 2))))
)

(procedure (localproc_0163 param1)
	(cond 
		((< param1 0) (= param1 0))
		((> param1 11) (= param1 11))
	)
	(= local21 param1)
	(newView_3 setCel: local21 stopUpd:)
)

(procedure (localproc_0190 param1)
	(cond 
		((< param1 0) (= param1 0))
		((> param1 14) (= param1 14))
	)
	(= local22 param1)
	(newView_4 setCel: local22 stopUpd:)
)

(instance dagNabIt of Room
	(properties
		picture 340
		style IRISOUT
	)
	
	(method (init)
		(super init: &rest)
		(Load VIEW vDartGame)
		(Load VIEW vDartBoard)
		(Load SOUND (SoundFX 31))
		(Load SOUND (SoundFX 29))
		(Load SOUND (SoundFX 30))
		(SolvePuzzle POINTS_PLAYDAGNABIT 3 THIEF)
		(StatusLine code: dagStatus enable:)
		((View new:)
			view: vDartBoard
			loop: 0
			cel: 0
			posn: 70 45
			ignoreActors:
			setPri: 0
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vDartBoard
			loop: 1
			cel: 0
			posn: 68 183
			ignoreActors:
			setPri: 13
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vDartBoard
			loop: 2
			cel: 0
			posn: 183 180
			ignoreActors:
			setPri: 1
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vDartBoard
			loop: 2
			cel: 1
			posn: 272 180
			ignoreActors:
			setPri: 1
			init:
			stopUpd:
			addToPic:
		)
		((= egoNamePlate (View new:))
			view: vDartBoard
			loop: 5
			cel: 0
			ignoreActors:
			posn: 27 178
			setPri: 14
			init:
		)
		((= chiefNamePlate (View new:))
			view: vDartBoard
			loop: 5
			cel: 1
			ignoreActors:
			posn: 110 178
			setPri: 14
			init:
		)
		(dagMusic number: (SoundFX 31) init:)
		(self setScript: dagnabitScript)
		(keyDownHandler addToFront: dagnabitScript)
		(directionHandler addToFront: dagnabitScript)
		(mouseDownHandler addToFront: dagnabitScript)
	)
	
	(method (dispose)
		(StatusLine code: dftStatusCode)
		(super dispose:)
	)
)

(instance dagnabitScript of Script
	(properties)
	
	(method (init)
		(= bet 5)
		((= newView_3 (View new:))
			view: vDartBoard
			loop: 3
			cel: local21
			posn: 160 178
			ignoreActors:
			setPri: 2
			init:
			stopUpd:
		)
		((= newView_4 (View new:))
			view: vDartBoard
			loop: 4
			cel: local22
			posn: 272 178
			ignoreActors:
			setPri: 2
			init:
			stopUpd:
		)
		(localproc_0059)
		((= forceMenu (Prop new:))
			view: vDartGame
			setLoop: 2
			cel: 0
			posn: 900 0
			setPri: 6
			ignoreActors:
			init:
			stopUpd:
		)
		((= newAct (Actor new:))
			view: vDartGame
			setLoop: 0
			cel: 0
			posn: 900 100
			setPri: 4
			ignoreActors:
			ignoreHorizon:
			init:
			stopUpd:
		)
		((= newAct_2 (Actor new:))
			view: vDartGame
			setLoop: 6
			cel: 0
			posn: 900 200
			setPri: 7
			ignoreActors:
			init:
			stopUpd:
		)
		(super init: &rest)
	)

	(method (changeState newState)
		(switch (= state newState)
			(0
				;CI: Here's where the decompile choked... the Repeat.  I've reconsrucuted it best I can from the original asm.
				(repeat
					(= bet (GetNumber {"How much will you bet?"} bet))
					(if (<= bet 0)
						(HighPrint 340 2)
						;"If you don't bet, you can't play."
						(curRoom newRoom: 332)
						(break)
					else
						(if (> bet 50)
							(HighPrint 340 3)
							;"Now, let's not get carried away."
						else
							(if (not (Purchase bet))
								(HighPrint 340 4)
								;"Hey!  This is cash on the table.  You can't bet what you don't have."
							else
								(break)
							)
						)
					)
				)
				;; and the rest was able to be decompiled properly
				
				(if (>= totalDagNabItBet 100) 
					(HighPrint 340 5)
					;"So, you think you're pretty hot stuff, eh?  Well, we'll see about that."
				)
				(++ dartsBonus)
				(= local16 (= local12 (= egoScore (= chiefScore 0))))
				(= local20 (- (Random 60 90) bet))
				(= local35 0)
				(DisplayDartScore egoScore 14 47 173)
				(DisplayDartScore chiefScore 11 73 173)
				(self cue:)
			)
			(1
				(HandsOff)
				(= local13 0)
				(= local18 1)
				(localproc_0059)
				(self setScript: blinkScript)
			)
			(2
				(localproc_006e)
				(self setScript: throwScript)
				(self cue:)
			)
			(3
				(forceMenu cel: 0 posn: local8 local9 forceUpd:)
				(newAct_2
					setLoop: 6
					cel: 0
					posn: local8 local9
					setPri: 7
					forceUpd:
				)
				(HandsOn)
				(= local17 1)
			)
			(4
				(= local17 0)
				(HandsOff)
				(localproc_0088)
				(script changeState: 1)
			)
			(5
				(DisplayDartScore egoScore 14 47 173)
				(if (< (++ local13) 3)
					(localproc_0059)
					(self changeState: 3)
				else
					(forceMenu posn: 900 0)
					(self cue:)
				)
			)
			(6
				(egoNamePlate show:)
				(chiefNamePlate hide:)
				(= local18 0)
				(= local13 0)
				(localproc_0059)
				(self setScript: blinkScript)
			)
			(7
				(self setScript: throwScript)
				(self cue:)
			)
			(8
				(forceMenu cel: 0 posn: local8 local9 forceUpd:)
				(= cycles 20)
			)
			(9
				(localproc_0107)
				(++ local16)
				(script changeState: 1)
			)
			(10
				(DisplayDartScore chiefScore 11 73 173)
				(if (< (++ local13) 3)
					(self changeState: 8)
				else
					(chiefNamePlate show:)
					(forceMenu posn: 900 0)
					(++ local12)
					(= cycles 5)
				)
			)
			(11
				(if (< local12 3)
					(self changeState: 1)
				else
					(self cue:)
				)
			)
			(12
				(HandsOn)
				(cond 
					((> egoScore chiefScore)
						(ego get: iSilver (+ bet bet))
						(HighPrint 340 6)
						;"You got me that time.  Say, you're pretty tough!"
						
						(if (>= bet 25) 
							(SolvePuzzle POINTS_WINDAGNABITSMALL 5 THIEF)
						)
						(= totalDagNabItBet (+ totalDagNabItBet bet))
					)
					((< egoScore chiefScore)
						(HighPrint 340 7)
						;"Looks like I won that one.  Must have gotten lucky."
						(= totalDagNabItBet (- totalDagNabItBet bet))
					)
					(else
						(ego get: iSilver bet)
						(if (<= bet 25)
							(HighPrint 340 8)
							;"It's a tie.  Let's double the wager."
							(= bet (+ bet bet))
						else
							(HighPrint 340 9)
							;"Hey, nice game.  It's a tie."
						)
					)
				)
				(localproc_006e)
				(Print 340 10)
				; Shall we play again?
				(self setScript: askPlayAgain self)
			)
			(13
				(if local7
					(self changeState: 0)
				else
					(curRoom newRoom: 332)
				)
			)
		)
	)
	
;;; 	(method (changeState newState)
;;; 		(asm
;;; 			lap      newState
;;; 			aTop     state
;;; 			push    
;;; 			dup     
;;; 			ldi      0
;;; 			eq?     
;;; 			bnt      code_0812
;;; code_0760:
;;; 			pushi    2
;;; 			lofsa    {"How much will you bet?"}
;;; 			push    
;;; 			lsl      bet
;;; 			calle    GetNumber,  4
;;; 			sal      bet
;;; 			push    
;;; 			ldi      0
;;; 			le?     
;;; 			bnt      code_078b
;;; 			pushi    2
;;; 			pushi    340
;;; 			pushi    2
;;; 			callb    HighPrint,  4
;;; 			pushi    #newRoom
;;; 			pushi    1
;;; 			pushi    332
;;; 			lag      curRoom
;;; 			send     6
;;; 			ret     
;;; 			jmp      code_0760
;;; code_078b:
;;; 			lsl      bet
;;; 			ldi      50
;;; 			gt?     
;;; 			bnt      code_079f
;;; 			pushi    2
;;; 			pushi    340
;;; 			pushi    3
;;; 			callb    HighPrint,  4
;;; 			jmp      code_0760
;;; code_079f:
;;; 			pushi    1
;;; 			lsl      bet
;;; 			callb    Purchase,  2
;;; 			not     
;;; 			bnt      code_07bb
;;; 			pushi    2
;;; 			pushi    340
;;; 			pushi    4
;;; 			callb    HighPrint,  4
;;; 			jmp      code_0760
;;; 			jmp      code_07bb
;;; 			jmp      code_0760
;;; code_07bb:
;;; 			lsg      totalDagNabItBet
;;; 			ldi      100
;;; 			ge?     
;;; 			bnt      code_07cd
;;; 			pushi    2
;;; 			pushi    340
;;; 			pushi    5
;;; 			callb    HighPrint,  4
;;; code_07cd:
;;; 			+ag      dartsBonus
;;; 			ldi      0
;;; 			sal      chiefScore
;;; 			sal      egoScore
;;; 			sal      local12
;;; 			sal      local16
;;; 			pushi    2
;;; 			pushi    60
;;; 			pushi    90
;;; 			callk    Random,  4
;;; 			push    
;;; 			lal      bet
;;; 			sub     
;;; 			sal      local20
;;; 			ldi      0
;;; 			sal      local35
;;; 			pushi    4
;;; 			lsl      egoScore
;;; 			pushi    14
;;; 			pushi    47
;;; 			pushi    173
;;; 			call     DisplayDartScore,  8
;;; 			pushi    4
;;; 			lsl      chiefScore
;;; 			pushi    11
;;; 			pushi    73
;;; 			pushi    173
;;; 			call     DisplayDartScore,  8
;;; 			pushi    #cue
;;; 			pushi    0
;;; 			self     4
;;; 			jmp      code_0ab4
;;; code_0812:
;;; 			dup     
;;; 			ldi      1
;;; 			eq?     
;;; 			bnt      code_0836
;;; 			pushi    0
;;; 			callb    HandsOff,  0
;;; 			ldi      0
;;; 			sal      local13
;;; 			ldi      1
;;; 			sal      local18
;;; 			pushi    0
;;; 			call     localproc_0059,  0
;;; 			pushi    #setScript
;;; 			pushi    1
;;; 			lofsa    blinkScript
;;; 			push    
;;; 			self     6
;;; 			jmp      code_0ab4
;;; code_0836:
;;; 			dup     
;;; 			ldi      2
;;; 			eq?     
;;; 			bnt      code_0853
;;; 			pushi    0
;;; 			call     localproc_006e,  0
;;; 			pushi    #setScript
;;; 			pushi    1
;;; 			lofsa    throwScript
;;; 			push    
;;; 			self     6
;;; 			pushi    #cue
;;; 			pushi    0
;;; 			self     4
;;; 			jmp      code_0ab4
;;; code_0853:
;;; 			dup     
;;; 			ldi      3
;;; 			eq?     
;;; 			bnt      code_0898
;;; 			pushi    #cel
;;; 			pushi    1
;;; 			pushi    0
;;; 			pushi    196
;;; 			pushi    2
;;; 			lsl      local8
;;; 			lsl      local9
;;; 			pushi    198
;;; 			pushi    0
;;; 			lal      forceMenu
;;; 			send     18
;;; 			pushi    #setLoop
;;; 			pushi    1
;;; 			pushi    6
;;; 			pushi    7
;;; 			pushi    1
;;; 			pushi    0
;;; 			pushi    196
;;; 			pushi    2
;;; 			lsl      local8
;;; 			lsl      local9
;;; 			pushi    66
;;; 			pushi    1
;;; 			pushi    7
;;; 			pushi    198
;;; 			pushi    0
;;; 			lal      newAct_2
;;; 			send     30
;;; 			pushi    0
;;; 			callb    HandsOn,  0
;;; 			ldi      1
;;; 			sal      local17
;;; 			jmp      code_0ab4
;;; code_0898:
;;; 			dup     
;;; 			ldi      4
;;; 			eq?     
;;; 			bnt      code_08b7
;;; 			ldi      0
;;; 			sal      local17
;;; 			pushi    0
;;; 			callb    HandsOff,  0
;;; 			pushi    0
;;; 			call     localproc_0088,  0
;;; 			pushi    #changeState
;;; 			pushi    1
;;; 			pushi    1
;;; 			pToa     script
;;; 			send     6
;;; 			jmp      code_0ab4
;;; code_08b7:
;;; 			dup     
;;; 			ldi      5
;;; 			eq?     
;;; 			bnt      code_08f9
;;; 			pushi    4
;;; 			lsl      egoScore
;;; 			pushi    14
;;; 			pushi    47
;;; 			pushi    173
;;; 			call     DisplayDartScore,  8
;;; 			+al      local13
;;; 			push    
;;; 			ldi      3
;;; 			lt?     
;;; 			bnt      code_08e5
;;; 			pushi    0
;;; 			call     localproc_0059,  0
;;; 			pushi    #changeState
;;; 			pushi    1
;;; 			pushi    3
;;; 			self     6
;;; 			jmp      code_0ab4
;;; code_08e5:
;;; 			pushi    #posn
;;; 			pushi    2
;;; 			pushi    900
;;; 			pushi    0
;;; 			lal      forceMenu
;;; 			send     8
;;; 			pushi    #cue
;;; 			pushi    0
;;; 			self     4
;;; 			jmp      code_0ab4
;;; code_08f9:
;;; 			dup     
;;; 			ldi      6
;;; 			eq?     
;;; 			bnt      code_0929
;;; 			pushi    #show
;;; 			pushi    0
;;; 			lal      egoNamePlate
;;; 			send     4
;;; 			pushi    #hide
;;; 			pushi    0
;;; 			lal      chiefNamePlate
;;; 			send     4
;;; 			ldi      0
;;; 			sal      local18
;;; 			ldi      0
;;; 			sal      local13
;;; 			pushi    0
;;; 			call     localproc_0059,  0
;;; 			pushi    #setScript
;;; 			pushi    1
;;; 			lofsa    blinkScript
;;; 			push    
;;; 			self     6
;;; 			jmp      code_0ab4
;;; code_0929:
;;; 			dup     
;;; 			ldi      7
;;; 			eq?     
;;; 			bnt      code_0941
;;; 			pushi    #setScript
;;; 			pushi    1
;;; 			lofsa    throwScript
;;; 			push    
;;; 			self     6
;;; 			pushi    #cue
;;; 			pushi    0
;;; 			self     4
;;; 			jmp      code_0ab4
;;; code_0941:
;;; 			dup     
;;; 			ldi      8
;;; 			eq?     
;;; 			bnt      code_0963
;;; 			pushi    #cel
;;; 			pushi    1
;;; 			pushi    0
;;; 			pushi    196
;;; 			pushi    2
;;; 			lsl      local8
;;; 			lsl      local9
;;; 			pushi    198
;;; 			pushi    0
;;; 			lal      forceMenu
;;; 			send     18
;;; 			ldi      20
;;; 			aTop     cycles
;;; 			jmp      code_0ab4
;;; code_0963:
;;; 			dup     
;;; 			ldi      9
;;; 			eq?     
;;; 			bnt      code_097c
;;; 			pushi    0
;;; 			call     localproc_0107,  0
;;; 			+al      local16
;;; 			pushi    #changeState
;;; 			pushi    1
;;; 			pushi    1
;;; 			pToa     script
;;; 			send     6
;;; 			jmp      code_0ab4
;;; code_097c:
;;; 			dup     
;;; 			ldi      10
;;; 			eq?     
;;; 			bnt      code_09c2
;;; 			pushi    4
;;; 			lsl      chiefScore
;;; 			pushi    11
;;; 			pushi    73
;;; 			pushi    173
;;; 			call     DisplayDartScore,  8
;;; 			+al      local13
;;; 			push    
;;; 			ldi      3
;;; 			lt?     
;;; 			bnt      code_09a5
;;; 			pushi    #changeState
;;; 			pushi    1
;;; 			pushi    8
;;; 			self     6
;;; 			jmp      code_0ab4
;;; code_09a5:
;;; 			pushi    #show
;;; 			pushi    0
;;; 			lal      chiefNamePlate
;;; 			send     4
;;; 			pushi    #posn
;;; 			pushi    2
;;; 			pushi    900
;;; 			pushi    0
;;; 			lal      forceMenu
;;; 			send     8
;;; 			+al      local12
;;; 			ldi      5
;;; 			aTop     cycles
;;; 			jmp      code_0ab4
;;; code_09c2:
;;; 			dup     
;;; 			ldi      11
;;; 			eq?     
;;; 			bnt      code_09e2
;;; 			lsl      local12
;;; 			ldi      3
;;; 			lt?     
;;; 			bnt      code_09da
;;; 			pushi    #changeState
;;; 			pushi    1
;;; 			pushi    1
;;; 			self     6
;;; 			jmp      code_0ab4
;;; code_09da:
;;; 			pushi    #cue
;;; 			pushi    0
;;; 			self     4
;;; 			jmp      code_0ab4
;;; code_09e2:
;;; 			dup     
;;; 			ldi      12
;;; 			eq?     
;;; 			bnt      code_0a94
;;; 			pushi    0
;;; 			callb    HandsOn,  0
;;; 			lsl      egoScore
;;; 			lal      chiefScore
;;; 			gt?     
;;; 			bnt      code_0a2c
;;; 			pushi    237
;;; 			pushi    #-info-
;;; 			pushi    1
;;; 			lsl      bet
;;; 			lal      bet
;;; 			add     
;;; 			push    
;;; 			lag      ego
;;; 			send     8
;;; 			pushi    2
;;; 			pushi    340
;;; 			pushi    6
;;; 			callb    HighPrint,  4
;;; 			lsl      bet
;;; 			ldi      25
;;; 			ge?     
;;; 			bnt      code_0a20
;;; 			pushi    3
;;; 			pushi    644
;;; 			pushi    5
;;; 			pushi    2
;;; 			callb    SolvePuzzle,  6
;;; code_0a20:
;;; 			lsg      totalDagNabItBet
;;; 			lal      bet
;;; 			add     
;;; 			sag      totalDagNabItBet
;;; 			jmp      code_0a78
;;; code_0a2c:
;;; 			lsl      egoScore
;;; 			lal      chiefScore
;;; 			lt?     
;;; 			bnt      code_0a49
;;; 			pushi    2
;;; 			pushi    340
;;; 			pushi    7
;;; 			callb    HighPrint,  4
;;; 			lsg      totalDagNabItBet
;;; 			lal      bet
;;; 			sub     
;;; 			sag      totalDagNabItBet
;;; 			jmp      code_0a78
;;; code_0a49:
;;; 			pushi    #get
;;; 			pushi    2
;;; 			pushi    1
;;; 			lsl      bet
;;; 			lag      ego
;;; 			send     8
;;; 			lsl      bet
;;; 			ldi      25
;;; 			le?     
;;; 			bnt      code_0a6f
;;; 			pushi    2
;;; 			pushi    340
;;; 			pushi    8
;;; 			callb    HighPrint,  4
;;; 			lsl      bet
;;; 			lal      bet
;;; 			add     
;;; 			sal      bet
;;; 			jmp      code_0a78
;;; code_0a6f:
;;; 			pushi    2
;;; 			pushi    340
;;; 			pushi    9
;;; 			callb    HighPrint,  4
;;; code_0a78:
;;; 			pushi    0
;;; 			call     localproc_006e,  0
;;; 			pushi    2
;;; 			pushi    340
;;; 			pushi    10
;;; 			calle    Print,  4
;;; 			pushi    #setScript
;;; 			pushi    2
;;; 			lofsa    askPlayAgain
;;; 			push    
;;; 			pushSelf
;;; 			self     8
;;; 			jmp      code_0ab4
;;; code_0a94:
;;; 			dup     
;;; 			ldi      13
;;; 			eq?     
;;; 			bnt      code_0ab4
;;; 			lal      local7
;;; 			bnt      code_0aa9
;;; 			pushi    #changeState
;;; 			pushi    1
;;; 			pushi    0
;;; 			self     6
;;; 			jmp      code_0ab4
;;; code_0aa9:
;;; 			pushi    #newRoom
;;; 			pushi    1
;;; 			pushi    332
;;; 			lag      curRoom
;;; 			send     6
;;; code_0ab4:
;;; 			toss    
;;; 			ret     
;;; 		)
;;; 	)
	
	(method (handleEvent event &tmp temp0 temp1 temp2)
		(= temp0 (event message?))
		(if (super handleEvent: event) (return 1))
		(return
			(switch (event type?)
				(mouseDown
					(if local17
						(event claimed: TRUE)
						(= temp1 (event x?))
						(= temp2 (event y?))
						(cond 
							((localproc_000c temp1 temp2 33 17 107 76) (self cue:))
							((localproc_000c temp1 temp2 149 154 176 166) (localproc_0163 (- local21 1)))
							((localproc_000c temp1 temp2 189 154 218 166) (localproc_0163 (+ local21 1)))
							((localproc_000c temp1 temp2 238 150 250 162) (localproc_0190 (- local22 1)))
							((localproc_000c temp1 temp2 294 150 305 162) (localproc_0190 (+ local22 1)))
							((localproc_000c temp1 temp2 155 172 211 180) (localproc_0163 (/ (- temp1 160) 4)))
							((localproc_000c temp1 temp2 245 163 298 180) (localproc_0190 (/ (- temp1 249) 3)))
						)
					)
				)
				(keyDown
					(cond 
						((== 63 temp0) (event claimed: 1) (DagNabItHelp))
						((and local17 (== 13 temp0)) (event claimed: 1) (self cue:))
					)
				)
				(saidEvent
					(cond 
						((Said 'cease,end,done,quit,done') (curRoom newRoom: 332))
						(
							(or
								(Said 'ask//game,dagnabit,play,rule')
								(Said 'help,rule')
							)
							(DagNabItHelp)
						)
						((Said 'bet,play,begin') (HighPrint 340 1))
					)
				)
				(direction
					(event claimed: TRUE)
					(if local17
						(switch (event message?)
							(dirN
								(localproc_0163 (+ local21 1))
							)
							(dirS
								(localproc_0163 (- local21 1))
							)
							(dirE
								(localproc_0190 (+ local22 1))
							)
							(dirW
								(localproc_0190 (- local22 1))
							)
						)
					)
				)
			)
		)
	)
)

(instance askPlayAgain of Script
	(properties)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((!= (event type?) saidEvent))
			((Said 'affirmative,please,play') (= local7 1) (self dispose:))
			((Said 'n,cease,quit') (= local7 0) (self dispose:))
			(else (event claimed: TRUE) (HighPrint 340 11))
		)
	)
)

(instance throwScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0)
			(1
				(= theGNewSpeed speed)
				(theGame setSpeed: 0)
				(forceMenu cycleSpeed: 2 setCycle: CycleTo 2 1 self)
				(if local18 (newAct_2 cycleSpeed: 2 setCycle: CycleTo 2 1))
			)
			(2
				(forceMenu cycleSpeed: 0 setCycle: EndLoop self)
				(if local18 (newAct_2 cycleSpeed: 0 setCycle: EndLoop))
				(dagMusic number: (SoundFX 31) play:)
			)
			(3
				(forceMenu stopUpd:)
				(if local18 (newAct_2 posn: 900 200 stopUpd:))
				(newAct
					posn: local8 local9
					setPri: 4
					setLoop: (if local18 4 else 0)
					cel: 0
					cycleSpeed: 0
					moveSpeed: 0
					ignoreActors:
					ignoreControl: -1
					setCycle: EndLoop self
					setMotion: MoveTo local10 (+ local11 1)
				)
			)
			(4
				(newAct
					setPri: 3
					setLoop: (if local18 5 else 1)
					cel: 0
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Forward
					setMotion: MoveTo local10 local11 self
				)
			)
			(5
				(newAct posn: 900 100 stopUpd:)
				(forceMenu cel: 0 forceUpd: stopUpd:)
				((= [newView_5 local35] (View new:))
					view: vDartGame
					loop: (if local18 7 else 3)
					cel: 3
					posn: local10 local11
					ignoreActors:
					setPri: 1
					init:
					stopUpd:
				)
				(++ local35)
				(dagMusic stop:)
				(dagMusic
					number: (if local18 (SoundFX 29) else (SoundFX 30))
					play:
				)
				(= temp0
					(cond 
						((< local10 65) 0)
						((> local10 75) 2)
						(else 1)
					)
				)
				(= local10 (+ (* (- local10 70) 2) 229))
				(= local11 (+ (* (- local11 45) 2) 61))
				((= [newView_5 local35] (View new:))
					view: vDartGame
					loop: (if local18 7 else 3)
					cel: temp0
					posn: local10 local11
					ignoreActors:
					setPri: 1
					init:
					stopUpd:
				)
				(++ local35)
				(= temp1
					(switch (OnControl CMAP local10 local11)
						(cBLUE 1)
						(cGREEN 2)
						(cCYAN 3)
						(cRED 4)
						(else  0)
					)
				)
				(if local18
					(= egoScore (+ egoScore temp1))
				else
					(= chiefScore (+ chiefScore temp1))
				)
				(theGame setSpeed: theGNewSpeed)
				(client cue:)
			)
		)
	)
)

(instance blinkScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(= temp0 (if local18 egoNamePlate else chiefNamePlate))
		(switch (= state newState)
			(0 (= register 3) (self cue:))
			(1
				(temp0 hide:)
				(if (not (-- register)) (self changeState: 3))
				(= cycles 6)
			)
			(2
				(temp0 show:)
				(= state 0)
				(= cycles 6)
			)
			(3 (client cue:))
		)
	)
)

(instance dagMusic of Sound
	(properties
		number 31
	)
)

(instance dagStatus of Code
	(properties)
	
	(method (doit strg)
		(Format strg 340 12 score)
	)
)
