;;; Sierra Script 1.0 - (do not remove this comment)
(script# 361)
(include game.sh)
(use Main)
(use User)
(use System)

(public
	AddExtras 0
	DisposeExtras 1
)

(local
	saveCanControl
	saveCanInput
	saveATPs
)
(procedure (AddExtras param1 param2 &tmp [temp0 2] temp2 temp3)
	(asm
		pushi    #controls
		pushi    0
		class    User
		send     4
		sal      saveCanControl
		pushi    #canInput
		pushi    0
		class    User
		send     4
		sal      saveCanInput
		pushi    #canControl
		pushi    1
		pushi    0
		pushi    226
		pushi    1
		pushi    0
		class    User
		send     12
		lag      addToPics
		sal      saveATPs
		ldi      0
		sag      addToPics
		pushi    #drawPic
		pushi    1
		lsp      param1
		lag      curRoom
		send     6
		pushi    #eachElementDo
		pushi    1
		pushi    137
		lag      cast
		send     6
		ldi      0
		sat      temp2
code_006e:
		lst      temp2
		lsp      argc
		ldi      1
		sub     
		lt?     
		bnt      code_0093
		pushi    #init
		pushi    0
		lat      temp2
		lapi     param2
		send     4
		pushi    #add
		pushi    1
		lat      temp2
		lspi     param2
		lofsa    extraList
		send     6
		+at      temp2
		jmp      code_006e
code_0093:
		pushi    #type
		pushi    0
		pushi    #new
		pushi    1
		pushi    5
		class    Event
		send     6
		sat      temp3
		send     4
		push    
		ldi      0
		eq?     
		bnt      code_00d3
		pushi    #dispose
		pushi    0
		lat      temp3
		send     4
		pushi    1
		lsg      speed
		callk    Wait,  2
		pushi    2
		pushi    #elements
		pushi    0
		lofsa    extraList
		send     4
		push    
		pushi    1
		callk    Animate,  4
		pushi    #eachElementDo
		pushi    1
		pushi    162
		lofsa    extraList
		send     6
		jmp      code_0093
code_00d3:
		pushi    #dispose
		pushi    0
		lat      temp3
		send     4
		pushi    0
		call     DisposeExtras,  0
		ret     
	)
)

(procedure (DisposeExtras &tmp [temp0 3])
	(curRoom drawPic: (curRoom picture?))
	(= addToPics saveATPs)
	(extraList
		eachElementDo: #dispose
		eachElementDo: #delete
		release:
		dispose:
	)
	(addToPics doit:)
	(cast eachElementDo: #show)
	(User canControl: saveCanControl canInput: saveCanInput)
	(DisposeScript 361)
)

(instance extraList of Collection)
