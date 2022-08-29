;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
(include game.sh) (include "50.shm")
(use Main)
(use RoomTeller)
(use Recorder)
(use Print)
(use Window)
(use Game)
(use System)

(public
	interrogation 0
)

(local
	currentTeller
	local1
	local2
	saveWindow
	local4
)
(procedure (localproc_095e flagEnum)
	(return
		(not
			(&
				[gameFlags (/ flagEnum 16)]
				(>> $8000 (mod flagEnum 16))
			)
		)
	)
)

(procedure (InterrogateTst flagEnum)
	(return
		(&
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (InterrogateSet flagEnum &tmp oldState)
	(= oldState (InterrogateTst flagEnum))
	(= [gameFlags (/ flagEnum 16)]
		(|
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
	(return oldState)
)

(procedure (InterrogateClr flagEnum &tmp oldState)
	(= oldState (InterrogateTst flagEnum))
	(= [gameFlags (/ flagEnum 16)]
		(&
			[gameFlags (/ flagEnum 16)]
			(~ (>> $8000 (mod flagEnum 16)))
		)
	)
	(return oldState)
)

(procedure (localproc_09d0 flagEnum)
	(return
		(not
			(&
				[questionsAsked (/ flagEnum 16)]
				(>> $8000 (mod flagEnum 16))
			)
		)
	)
)

(procedure (localproc_09e3 flagEnum)
	(return
		(&
			[questionsAsked (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (localproc_09f5 flagEnum &tmp oldState)
	(= oldState (localproc_09e3 flagEnum))
	(= [questionsAsked (/ flagEnum 16)]
		(|
			[questionsAsked (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
	(return oldState)
)

(instance interrogation of Room
	(properties
		picture 98
		style FADEOUT
	)
	
	(method (init &tmp i)
		(if (== interrogateSubject GRACE)
			(= local4 1)
		)
		(if local4
			(Load RES_PIC (= picture 99))
		else
			(Load RES_PIC 98)
		)
		(ScriptID 51)
		(ScriptID 90)
		(ScriptID TELLER)
		(Load RES_VIEW 970)
		(super init:)
		(= i 37)
		(while (< i 40)
			(InterrogateSet i)
			(++ i)
		)
		(= saveWindow systemWindow)
		(= systemWindow Window)
		(self setScript: roomScript)
	)
	
	(method (dispose)
		(UnLoad RES_PIC (if local4 98 else 99))
		(DisposeScript 51)
		(DisposeScript 90)
		(DisposeScript TELLER)
		(DisposeScript 941)
		(DisposeScript tlkEgo)
		(UnLoad RES_VIEW 970)
		(= systemWindow saveWindow)
		(if (IsObject gNewList)
			(gNewList dispose:)
			(= gNewList 0)
		)
		(super dispose:)
	)
)

(instance roomScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register theCursor)
				(theGame setCursor: ARROW_CURSOR)
				(= currentTeller
					(switch interrogateSubject
						(GRACE (grace init: 0))
						(SARGE (sarge init: 1))
						(else  0)
					)
				)
				(if local4
					(DrawCel 9701 0 0 2 0 13)
					((RestoreCel new:) init: 970 3 0 2 0 13)
				)
				(= seconds 1)
			)
			(1
				(if currentTeller
					(if 0
						(recorderFile init:)
						(currentTeller
							transcript: (recorderFile readTeller: (currentTeller index?))
						)
					)
					(= ticks 20)
				else
					(messager say: N_GABRIEL NULL C_NOBODY_HERE 0 self)
				)
			)
			(2
				(if currentTeller
					(currentTeller doVerb:)
				else
					(++ state)
					(= ticks 20)
				)
			)
			(3
				(if 0
					(recorderFile
						writeTeller: (currentTeller transcript?)
						dispose:
					)
				)
				(= ticks 20)
			)
			(4
				(if currentTeller
					(currentTeller dispose:)
				)
				(theGame setCursor: register)
				(= ticks 20)
			)
			(5
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance sarge of RoomTeller
	(properties
		curNoun N_SARGE
		verb V_ASK_SARGE
		index 1
		view 972
		color 60
	)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (dispose)
		(DisposeScript tlkSarge)
		(super dispose: &rest)
	)
	
	(method (sayMessage)
		(messager say: sayNoun verb iconValue 0 self)
		(if (IsObject transcript)
			(transcript addToEnd: iconValue)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				C_CRIME_SCENE (if (InterrogateTst 76) (localproc_095e 11) else 0)
				C_PHOTOGRAPHS (localproc_095e 12)
		)
	)
	
	(method (cue)
		(switch iconValue
			(22
				(InterrogateSet 12)
				(self doVerb:)
			)
			(18
				(= iconValue 57)
				(if (localproc_09f5 28)
					(++ iconValue)
					(if (localproc_09f5 29) (++ iconValue))
				)
				(self sayMessage:)
			)
			(3
				(= iconValue 68)
				(if (localproc_09f5 30)
					(++ iconValue)
					(if (localproc_09f5 31) (++ iconValue))
				)
				(self sayMessage:)
			)
			(8
				(= iconValue 60)
				(if (localproc_09f5 32) (++ iconValue))
				(self sayMessage:)
			)
			(5
				(= iconValue 71)
				(if (localproc_09f5 33) (++ iconValue))
				(self sayMessage:)
			)
			(13
				(= iconValue 51)
				(if (localproc_09f5 26)
					(++ iconValue)
					(if (localproc_09f5 27) (++ iconValue))
				)
				(self sayMessage:)
			)
			(19
				(InterrogateSet 66)
				(= iconValue
					(cond 
						((localproc_095e 11) (if (InterrogateTst 76) 63 else 62))
						((localproc_095e 16) 64)
						((< currentDay 6) 65)
						((localproc_095e 76) 66)
						(else 67)
					)
				)
				(self sayMessage:)
			)
			(62
				(InterrogateSet 76)
				(ego getPoints: 122 2 1)
				(self doVerb:)
			)
			(64
				(InterrogateClr 76)
				(InterrogateSet 99)
				(self doVerb:)
			)
			(66
				(InterrogateSet 76)
				(self doVerb:)
			)
			(17
				(= iconValue 54)
				(if (InterrogateSet 94)
					(++ iconValue)
					(if (localproc_09f5 34) (++ iconValue))
				)
				(self sayMessage:)
			)
			(else  (self doVerb:))
		)
	)
	
	(method (setPersonals)
		(= personals (List new:))
		(personals add: 25 26 27 105 29)
	)
)

(instance grace of RoomTeller
	(properties
		curNoun 4
		verb 54
		view 971
		color 21
	)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (dispose)
		(DisposeScript 1101)
		(super dispose: &rest)
	)
	
	(method (sayMessage)
		(messager say: sayNoun verb iconValue 0 self)
		(if (IsObject transcript)
			(transcript addToEnd: iconValue)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				23
				(localproc_09d0 9)
				24
				(localproc_09d0 10)
				16
				(localproc_09d0 11)
				21
				(localproc_09d0 12)
				29
				(InterrogateTst 70)
				47
				(if (InterrogateTst 71) (localproc_095e 72) else 0)
				49
				(InterrogateTst 65)
				48
				(if (InterrogateTst 51) (localproc_095e 73) else 0)
				50
				(if (ego has: 35) (localproc_095e 36) else 0)
				26
				(if (not local1) else (not local2))
				27
				local2
		)
	)
	
	(method (cue)
		(switch iconValue
			(8
				(= iconValue 60)
				(if (localproc_09f5 13) (++ iconValue))
				(self sayMessage:)
			)
			(13
				(= iconValue 51)
				(if (localproc_09f5 14) (++ iconValue))
				(self sayMessage:)
			)
			(25
				(= iconValue 73)
				(if (localproc_09f5 8) (++ iconValue))
				(self sayMessage:)
			)
			(4
				(= iconValue 91)
				(if (localproc_09f5 19) (++ iconValue))
				(self sayMessage:)
			)
			(14
				(= iconValue 89)
				(if (localproc_09f5 20) (++ iconValue))
				(self sayMessage:)
			)
			(10
				(= iconValue 93)
				(if (localproc_09f5 18) (++ iconValue))
				(self sayMessage:)
			)
			(23
				(localproc_09f5 9)
				(self doVerb:)
			)
			(24
				(localproc_09f5 10)
				(self doVerb:)
			)
			(16
				(localproc_09f5 11)
				(self doVerb:)
			)
			(20
				(= iconValue (Random 80 85))
				(self sayMessage:)
			)
			(104
				(self goTop:)
				(self doVerb:)
			)
			(21
				(localproc_09f5 12)
				(self doVerb:)
			)
			(29
				(InterrogateSet 55)
				(curRoom cue:)
			)
			(49
				(InterrogateSet 15)
				(InterrogateClr 65)
				(ego getPoints: 129 2 1)
				(self doVerb:)
			)
			(28
				(self goTop:)
				(self doVerb:)
			)
			(47
				(InterrogateSet 72)
				(self doVerb:)
			)
			(48
				(InterrogateSet 73)
				(self doVerb:)
			)
			(50
				(InterrogateSet 36)
				(self doVerb:)
			)
			(26
				(= local1 1)
				(= iconValue
					(if
						(cond 
							((== currentDay 1) (localproc_09d0 5))
							((== currentDay 3) (localproc_09d0 7))
							(else 0)
						)
						(= local2 1)
						(cond 
							((== currentDay 3)
								(if (localproc_09e3 6)
									(localproc_09f5 7)
									102
								else
									(localproc_09f5 6)
									101
								)
							)
							((localproc_09e3 4) (localproc_09f5 5) 78)
							((localproc_09e3 3) (localproc_09f5 4) 79)
							((localproc_09e3 2) (localproc_09f5 3) 77)
							(else (localproc_09f5 2) 76)
						)
					else
						75
					)
				)
				(self sayMessage:)
			)
			(78
				(InterrogateSet 63)
				(ego getPoints: 115 2 1)
				(self doVerb:)
			)
			(77
				(InterrogateSet 100)
				(self doVerb:)
			)
			(27
				(= iconValue
					(if
						(cond 
							((== currentDay 1) (localproc_09d0 5))
							((== currentDay 3) (localproc_09d0 7))
							(else 0)
						)
						(cond 
							((== currentDay 3) (localproc_09f5 7) 102)
							((localproc_09e3 4) (localproc_09f5 5) 78)
							((localproc_09e3 3) (localproc_09f5 4) 79)
							(else (localproc_09f5 3) 77)
						)
					else
						106
					)
				)
				(self sayMessage:)
			)
			(3
				(= iconValue
					(cond 
						((> currentDay 5) 70)
						((not (localproc_09f5 17)) 68)
						(else 69)
					)
				)
				(self sayMessage:)
			)
			(1
				(= iconValue
					(cond 
						((> currentDay 5) 97)
						((not (localproc_09f5 15)) 95)
						(else 96)
					)
				)
				(self sayMessage:)
			)
			(2
				(= iconValue
					(cond 
						((> currentDay 1) 100)
						((not (localproc_09f5 16)) 98)
						(else 99)
					)
				)
				(self sayMessage:)
			)
			(else  (self doVerb:))
		)
	)
	
	(method (setPersonals)
		(= personals (List new:))
		(personals add: 25 26 27 105 29)
	)
)

(instance recorderFile of RecordInfo
	(properties
		numTellers 2
		;name "RECORDER.DAT"
	)
	
	(method (init)
		(super init: &rest)
		(if (not (FileIO fileExists name))
			(Prints {Creating file: hit a key})
			(self create:)
		)
	)
)
