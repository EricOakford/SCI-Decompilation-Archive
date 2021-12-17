;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64029)
(include sci.sh)
(use Main)
(use oMessager)
(use String)
(use Array)
(use Messager)


(class TPMessager of Messager
	(properties
		scratch 0
		caller 0
		disposeWhenDone 1
		oneOnly 0
		killed 0
		disableIconBar 1
		oldIconBarState 0
		curSequence 0
		lastSequence 0
		talkerList 0
		bGameWasHandsOff 0
		bActive 0
		oTextSaver 0
		saveMessages 0
		lastNoun 0
		lastVerb 0
		lastCase 0
		lastMod 0
	)
	
	(method (cue param1 &tmp temp0 temp1 temp2)
		(asm
			_line_   106
			_file_   {filename}
			_line_   110
			lap      argc
			bnt      code_0278
			lap      param1
			bnt      code_0278
			_line_   111
			ldi      1
			aTop     killed
code_0278:
			_line_   115
			pToa     killed
			bnt      code_0319
			pToa     saveMessages
			bnt      code_0319
			pToa     oneOnly
			not     
			bnt      code_0319
			_line_   117
			pushi    #newWith
			pushi    2
			pushi    1000
			lofsa    {LOOKUP\_ERROR}
			push    
			class    Str
			send     8
			sat      temp2
			_line_   118
			pTos     curSequence
			ldi      1
			add     
			sat      temp0
			_line_   119
code_02ad:
			_line_   122
			pTos     lastSequence
			ldi      0
			gt?     
			bnt      code_02c2
			lst      temp0
			pToa     lastSequence
			gt?     
			bnt      code_02c2
			jmp      code_030d
			bnt      code_02c2
code_02c2:
			_line_   124
			pushi    7
			pushi    0
			pTos     lastMod
			pTos     lastNoun
			pTos     lastVerb
			pTos     lastCase
			lst      temp0
			pushi    #data
			pushi    0
			lat      temp2
			send     4
			push    
			callk    Message,  14
			sat      temp1
			_line_   127
			not     
			bnt      code_02e9
			jmp      code_030d
code_02e9:
			_line_   129
			pushi    #rememberMessage
			pushi    5
			pTos     lastMod
			pTos     lastNoun
			pTos     lastVerb
			pTos     lastCase
			lst      temp0
			pToa     oTextSaver
			send     14
			_line_   130
			lst      temp0
			ldi      1
			add     
			sat      temp0
			jmp      code_02ad
code_030d:
			_line_   132
			pushi    #dispose
			pushi    0
			lat      temp2
			send     4
code_0319:
			_line_   135
			pToa     oneOnly
			bt       code_0324
			pToa     killed
			bnt      code_0330
code_0324:
			_line_   136
			pushi    #dispose
			pushi    0
			self     4
			jmp      code_033d
code_0330:
			_line_   137
			_line_   138
			pushi    #nextMsg
			pushi    0
			self     4
code_033d:
			_line_   140
			ret     
		)
	)
	
	(method (nextMsg theLastMod theLastNoun theLastVerb theLastCase param5 &tmp temp0 temp1 temp2 temp3)
		(= temp1 (Str newWith: 1000 {}))
		(= temp2 0)
		(if argc
			(= temp0
				(Message
					0
					theLastMod
					theLastNoun
					theLastVerb
					theLastCase
					param5
					(temp1 data?)
				)
			)
			(= lastNoun theLastNoun)
			(= lastVerb theLastVerb)
			(= lastCase theLastCase)
			(= lastMod theLastMod)
		else
			(= temp0
				(Message
					0
					lastMod
					lastNoun
					lastVerb
					lastCase
					curSequence
					(temp1 data?)
				)
			)
		)
		(if
			(and
				temp0
				(or
					(not lastSequence)
					(and lastSequence (<= curSequence lastSequence))
				)
			)
			(= temp2
				(Str format: {%s: %s} (proc64032_0 temp0) temp1)
			)
			(if (!= (= temp0 (self findTalker: temp0)) -1)
				(if (& msgType $0002)
					(= temp3
						(IntArray
							with: lastMod lastNoun lastVerb lastCase curSequence
						)
					)
				)
				(talkerList add: temp0)
				(temp0 modNum: theLastMod say: temp2 temp3 self)
				(if (and saveMessages oTextSaver)
					(oTextSaver
						rememberMessage: lastMod lastNoun lastVerb lastCase curSequence
					)
				)
				(++ curSequence)
				(if (& msgType $0002) (temp3 dispose:))
			else
				(self dispose:)
			)
		else
			(self dispose:)
		)
		(temp1 dispose:)
		(if temp2 (temp2 dispose:))
	)
	
	(method (sayNoSave)
		(if saveMessages
			(= saveMessages 0)
			(self say: &rest)
			(= saveMessages 1)
		else
			(self say: &rest)
		)
	)
)
