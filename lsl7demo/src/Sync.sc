;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64929)
(include game.sh)
(use Main)
(use Timer)
(use Motion)
(use System)


(class Sync of Object
	(properties
		scratch 0
		syncTime -1
		syncCue $ffff
		prevCue $ffff
		syncNum -1
		modNum 0
		noun 0
		verb 0
		case 0
		seq 0
	)
	
	(method (syncStart theModNum theNoun theVerb theCase theSeq)
		(= modNum theModNum)
		(= noun theNoun)
		(= verb theVerb)
		(= case theCase)
		(= seq theSeq)
		(DoSync 0 self modNum noun verb case seq)
		(if (!= syncCue -1) (= prevCue syncCue) (= syncTime 0))
	)
	
	(method (syncCheck)
		(if
			(and
				(!= syncCue -1)
				(or
					(u<= syncTime syncBias)
					(<= syncTime (DoAudio 6 modNum noun verb case seq))
				)
			)
			(if (== (& $fff0 syncCue) 0)
				(= prevCue (| (& prevCue $fff0) syncCue))
			else
				(= prevCue syncCue)
			)
			(DoSync 1 self)
		)
	)
	
	(method (syncStop)
		(= prevCue -1)
		(DoSync 2)
	)
)

(class ScriptSync of Object
	(properties
		scratch 0
		prevSignal -1
		playing 0
	)
	
	(method (init param1 param2 param3 param4 param5)
		(if theSync (self cue:))
		((= theSync (Sync new:))
			init:
			syncStart: param1 param2 param3 param4 param5
		)
		(if (!= (theSync prevCue?) -1)
			(= playing 1)
			(regions add: self)
		)
		(Timer
			setTicks: self (DoAudio 1 param1 param2 param3 param4 param5)
		)
	)
	
	(method (doit &tmp temp0)
		(asm
			_line_   101
			_file_   {filename}
			_line_   102
			pushi    #prevCue
			pushi    0
			lag      theSync
			send     4
			push    
			ldi      65535
			ne?     
			bnt      code_0299
			_line_   103
code_0242:
			_line_   104
			pushi    #prevCue
			pushi    0
			lag      theSync
			send     4
			push    
			ldi      65535
			eq?     
			bnt      code_0259
			_line_   105
			jmp      code_028b
code_0259:
			_line_   107
			pushi    #syncTime
			pushi    0
			lag      theSync
			send     4
			sat      temp0
			_line_   108
			pushi    #syncCheck
			pushi    0
			lag      theSync
			send     4
			_line_   109
			lst      temp0
			pushi    #syncTime
			pushi    0
			lag      theSync
			send     4
			eq?     
			bnt      code_0242
			_line_   110
			jmp      code_028b
			jmp      code_0242
code_028b:
			_line_   113
			pushi    #prevCue
			pushi    0
			lag      theSync
			send     4
			aTop     prevSignal
code_0299:
			_line_   115
			ret     
		)
	)
	
	(method (cue)
		(FrameOut)
		(= playing 0)
		(= prevSignal 32767)
		(regions delete: self)
		(if theSync (theSync syncStop: dispose:) (= theSync 0))
	)
)

(class MouthSync of Cycle
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		clientLastCel 0
		oSpecialSync 0
		nSpecialSelector 69
		nCutoff 8
		bSpecial 0
	)
	
	(method (init param1 param2 param3 param4 param5 param6)
		(super init: param1)
		(if theSync (theSync syncStop: dispose:))
		((= theSync (Sync new:))
			syncStart: param2 param3 param4 param5 param6
		)
	)
	
	(method (doit &tmp temp0 theSyncSyncTime_2 theSyncSyncTime temp3)
		(super doit:)
		(if (!= (theSync prevCue?) -1)
			(= theSyncSyncTime (theSync syncTime?))
			(= temp3 0)
			(repeat
				(= theSyncSyncTime_2 (theSync syncTime?))
				(theSync syncCheck:)
				(breakif (== theSyncSyncTime_2 (theSync syncTime?)))
			)
			(if (!= theSyncSyncTime (theSync syncTime?))
				(if
				(>= (= temp0 (& $000f (theSync prevCue?))) nCutoff)
					(= bSpecial 1)
					(if oSpecialSync
						(Eval oSpecialSync nSpecialSelector temp0)
					)
				else
					(if (== bSpecial 1)
						(= bSpecial 0)
						(if oSpecialSync (Eval oSpecialSync nSpecialSelector 0))
					)
					(if (!= (client cel?) temp0) (client cel: temp0))
				)
			)
		else
			(if (== bSpecial (= completed 1))
				(= bSpecial 0)
				(if oSpecialSync (Eval oSpecialSync nSpecialSelector 0))
			)
			(client cel: 0)
			(self cycleDone:)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(if theSync (theSync syncStop: dispose:) (= theSync 0))
	)
	
	(method (cue)
		(if theSync
			(theSync syncStop: dispose:)
			(= theSync 0)
			(if caller (caller cue:) (= caller 0))
		)
	)
)
